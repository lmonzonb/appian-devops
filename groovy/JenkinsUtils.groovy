#!/usr/bin/env groovy

void runTestsVNC(propertyFile) {
  sh "cp devops/f4a/" + propertyFile + " f4a/FitNesseForAppian/fitnesse-automation.properties"
  dir("f4a/FitNesseForAppian") {
    wrap([$class:'Xvnc', useXauthority: true]) {
      sh script: "bash ./runFitNesseTest.sh"
    }
  }
}

void runTestsDocker(propertyFile) {
  sh "cp devops/f4a/" + propertyFile + " f4a/FitNesseForAppian/fitnesse-automation.properties"
  sh "docker-compose -f docker/docker-compose.yml up &"
  timeout(2) { //timeout is in minutes
    waitUntil {
      def numExpectedContainers = "2"
      def runningContainers = sh script: "docker ps --format {{.Names}} | grep \"fitnesse-\\(chrome\\|firefox\\)\" | wc -l", returnStdout: true
      runningContainers = runningContainers.trim()
      return (runningContainers == numExpectedContainers)
    }
  }
  sleep(10)
  dir("f4a/FitNesseForAppian") {
    sh script: "bash ./runFitNesseTest.sh"
  }
}

void runTestsDockerWithoutCompose(propertyFile, suiteFolder) {
  sh "cp devops/f4a/" + propertyFile + " f4a/FitNesseForAppian/fitnesse-automation.properties"
  sh "cp devops/f4a/users.properties f4a/FitNesseForAppian/configs/users.properties"
  sh "cp -r devops/f4a/test_suites/" + suiteFolder + " f4a/FitNesseForAppian/FitNesseRoot/FitNesseForAppian/Examples/" + suiteFolder
  setProperty("f4a/FitNesseForAppian/configs/users.properties", "${APPIAN_UI_CREDENTIALS_USR}", "${APPIAN_UI_CREDENTIALS_PSW}")
  setProperty("f4a/FitNesseForAppian/configs/custom.properties", "chrome.host.ip", "fitnesse-chrome-vnc")  
  setProperty("f4a/FitNesseForAppian/configs/custom.properties", "chrome.host.port", "4444")  
  
  sh "docker run -d -p 4444:4444 -p 35900:5900 --name fitnesse-chrome-vnc -v /dev/shm:/dev/shm selenium/standalone-chrome-debug &"
  timeout(2) { //timeout is in minutes
    waitUntil {
      def numExpectedContainers = "1"
      def runningContainers = sh script: "docker ps | grep \"fitnesse-chrome\" | wc -l", returnStdout: true
      runningContainers = runningContainers.trim()
      return (runningContainers == numExpectedContainers)
    }
  }
  sleep(10)
  sh "docker network connect localNetwork fitnesse-chrome-vnc"
  sleep(10)
  
  dir("f4a/FitNesseForAppian") {
    sh script: "bash ./runFitNesseTest.sh"
  }
}

void runTestsDockerWithWindowsHost(propertyFile, suiteFolder) {
  sh "cp devops/f4a/" + propertyFile + " f4a/FitNesseForAppian/fitnesse-automation.properties"
  sh "cp devops/f4a/users.properties f4a/FitNesseForAppian/configs/users.properties"
  sh "cp -r devops/f4a/test_suites/" + suiteFolder + " f4a/FitNesseForAppian/FitNesseRoot/FitNesseForAppian/Examples/" + suiteFolder
  setProperty("f4a/FitNesseForAppian/configs/users.properties", "${APPIAN_CREDENTIALS_USR}", "${APPIAN_CREDENTIALS_PSW}")
  setProperty("f4a/FitNesseForAppian/configs/custom.properties", "firefox.host.ip", "host.docker.internal")  
   
  dir("f4a/FitNesseForAppian") {
    sh script: "bash ./runFitNesseTest.sh"
  }
}

void runSpinUpAppianAndDeployWithCompose(propertyFile) {
  sh "cp devops/f4a/" + propertyFile + " f4a/FitNesseForAppian/fitnesse-automation.properties"
  sh "docker run -d -p 4444:4444 --name fitnesse-firefox -v /dev/shm:/dev/shm selenium/standalone-firefox &"
  timeout(2) { //timeout is in minutes
    waitUntil {
      def numExpectedContainers = "1"
      def runningContainers = sh script: "docker ps | grep \"fitnesse-firefox\" | wc -l", returnStdout: true
      runningContainers = runningContainers.trim()
      return (runningContainers == numExpectedContainers)
    }
  }
  sleep(10)
  dir("f4a/FitNesseForAppian") {
    sh script: "bash ./runFitNesseTest.sh"
  }
}

void retrieveLogs(propertyFile) {
  def test = sh script: "cat \"devops/f4a/${propertyFile}\" | grep \"testPath=\" | cut -d'=' -f2", returnStdout: true
  test = test.trim().minus(~"\\?.*")
  def zipName = "${test}_Results.zip"
  dir("f4a/FitNesseForAppian/FitNesseRoot/files/testResults") {
    sh "zip -r ${zipName} ${test}/**"
  }
  return "f4a/FitNesseForAppian/FitNesseRoot/files/testResults/${zipName}"
}

void buildPackage(versionPropertyFile) {
  sh "cp devops/adm/" + versionPropertyFile + " adm/appian-version-client/version-manager.properties"
  dir("adm/appian-version-client") {
    setProperty("version-manager.properties", "vcUsername", "${REPOUSERNAME}")
    setProperty("version-manager.properties", "vcPassword", "${REPOPASSWORD}")
    setProperty("version-manager.properties", "appianObjectsRepoPath", "appian/applications/${APPLICATIONNAME}")
    sh "./version-application.sh -package_path ../app-package.zip -local_repo_path ./local-repo"
  }
}

void importPackage(importPropertyFile, customProperties) {
  sh "cp devops/adm/" + importPropertyFile + " adm/appian-import-client/import-manager.properties"
  dir("adm/appian-import-client") {
  	setProperty("import-manager.properties", "url", "${APPIAN_DOCKER_SITE_URL}")
    setProperty("import-manager.properties", "username", "${APPIAN_CREDENTIALS_USR}")
    setProperty("import-manager.properties", "password", "${APPIAN_CREDENTIALS_PSW}")
    if (fileExists("../../appian/properties/${APPLICATIONNAME}/" + customProperties)) {
      setProperty("import-manager.properties", "importCustomizationPath", "../../appian/properties/${APPLICATIONNAME}/" + customProperties)
    }
   
	sh "./deploy-application.sh -application_path ../app-package.zip"
  }
}

void setProperty(filePath, property, propertyValue) {
  shNoTrace("sed -i -e 's|.\\?${property}=.*|${property}=${propertyValue}|' ${filePath}")
}

def shNoTrace(cmd) {
  sh '#!/bin/sh -e\n' + cmd
}

return this
