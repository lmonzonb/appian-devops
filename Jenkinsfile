pipeline {
  agent any
  tools {
        // Note: this should match with the tool name configured in your jenkins instance (JENKINS_URL/configureTools/)
        gradle "Gradle 6.3"
    }
  environment {
        // This can be nexus3 or nexus2
        NEXUS_VERSION = "nexus3"
        // This can be http or https
        NEXUS_PROTOCOL = "http"
        // Where your Nexus is running
        NEXUS_URL = "nexus-local:8081"
        // Repository where Appian applications are uploaded
        NEXUS_REPOSITORY = "appian-repository"
        // Repository where ALM tools are hosted
        NEXUS_ALM_REPOSITORY = "appian-alm-repository"
        // Jenkins credential id to authenticate to Nexus OSS
        NEXUS_CREDENTIALS = credentials('nexus-credentials')
        // Appian ADM file path in Nexus
        NEXUS_ADM_PATH = "appian-devops/adm.zip"
        // Appian ADM file name
        ADM_FILENAME = "adm.zip"
        
        // Appian F4A file path in Nexus
        NEXUS_F4A_PATH = "appian-devops/f4a.zip"
        // Appian F4A file name
        F4A_FILENAME = "f4a.zip"
        
        // Appian Application to deploy
        APPLICATIONNAME = "LMB_AD"
        // PATH of Appian Application to deploy
        APPLICATION_PATH = "appian/applications/LMB_AD/app-package.zip"
        
        
        // URL of the Appian site
        APPIAN_SITE_URL = "http://appian-lab.appiancorp.com:8080/suite"
        
       
       // Appian site credentials
       APPIAN_CREDENTIALS = credentials('appian-credentials')
       
        SITEPASSWORD_ENCODED = "pass"
        
        
        
    }
   
    
   
  stages {
  
  
    stage("Install ADM and FitNesse for Appian") {
      steps {
        script {
          def jenkinsUtils = load "groovy/JenkinsUtils.groovy"

          // Retrieve and setup ADM
          sh "rm -rf adm f4a"
          jenkinsUtils.shNoTrace("curl --user ${NEXUS_CREDENTIALS} \"${NEXUS_PROTOCOL}://${NEXUS_URL}/repository/${NEXUS_ALM_REPOSITORY}/${NEXUS_ADM_PATH}\" --output ${ADM_FILENAME}")
          sh "unzip adm.zip -d adm"
          sh "unzip adm/appian-adm-import*.zip -d adm/appian-import-client"
          jenkinsUtils.setProperty("adm/appian-import-client/metrics.properties", "pipelineUsage", "true")
          sh "unzip adm/appian-adm-versioning*.zip -d adm/appian-version-client"
          jenkinsUtils.setProperty("adm/appian-version-client/metrics.properties", "pipelineUsage", "true")

          // Retrieve and setup F4A
          jenkinsUtils.shNoTrace("curl --user ${NEXUS_CREDENTIALS} \"${NEXUS_PROTOCOL}://${NEXUS_URL}/repository/${NEXUS_ALM_REPOSITORY}/${NEXUS_F4A_PATH}\" --output ${F4A_FILENAME}")
          sh "unzip f4a.zip -d f4a"
          sh "unzip f4a/FitNesse*.zip -d f4a"
          jenkinsUtils.setProperty("f4a/FitNesseForAppian/configs/metrics.properties", "pipeline.usage", "true")
          sh "cp -a devops/f4a/test_suites/. f4a/FitNesseForAppian/FitNesseRoot/FitNesseForAppian/Examples/"
          sh "cp devops/f4a/users.properties f4a/FitNesseForAppian/configs/users.properties"

          // WebDriver Docker Container setup
          sh "docker pull selenium/standalone-firefox"
          jenkinsUtils.setProperty("f4a/FitNesseForAppian/configs/custom.properties", "firefox.host.port", "4444")
          jenkinsUtils.setProperty("f4a/FitNesseForAppian/configs/custom.properties", "chrome.host.port", "4445")
        }
      }
    }
    stage("Build Application Package from Repo") {
      steps {
        script {
          echo 'Build Application Package from Repo'
        }
      }
    }
    stage("Deploy to Test") {
      steps {
        script {
            def jenkinsUtils = load "groovy/JenkinsUtils.groovy"
            
            // Copy the package that will be imported
            sh "cp appian/applications/${APPLICATIONNAME}/app-package.zip adm/app-package.zip"
            
            jenkinsUtils.setProperty("adm/appian-import-client/import-manager.properties", "url", "${APPIAN_SITE_URL}")
            
          	jenkinsUtils.importPackage("import-manager.test.properties", "${APPLICATIONNAME}.test.properties")
        	echo 'Deploy to Test'
        }
      }
    }
    
    stage("Tag Successful Import into Test") {
      steps {
        script {
          echo 'Tag Successful Import into Test'
        }
      }
    }
    stage('Run Tests') {
            parallel {
    stage("Run Integration Tests") {
      steps {
        script {
          echo 'Run Integration Tests'
        }
      }
    }
    stage("Run Appian Rule Tests") {
            steps {
                script {
                    // Run gradle build to execute the Appian rule tests
                    sh "gradle build -b devops/rule_testing/build.gradle runApplicationTest -PsiteUrl=${APPIAN_SITE_URL} -PappianUserName=${SITEUSERNAME} -PappianPasswordEncoded=${SITEPASSWORD_ENCODED}"
                }
            }
            post {
        		always {
            		junit 'devops/rule_testing/reports/**/*.xml'
        }
    }
        }
   }
   }
    stage("Deploy to Staging") {
      steps {
        script {
          echo 'Deploy to Staging'
        }
      }
    }
    stage("Tag Successful Import into Staging") {
      steps {
        script {
          echo 'Tag Successful Import into Staging'
        }
      }
    }
   stage("Run Acceptance Tests") {
     steps {
        script {
          def jenkinsUtils = load "groovy/JenkinsUtils.groovy"
          jenkinsUtils.runTestsDockerWithoutCompose("fitnesse-automation.acceptance.properties")
        }
      }
      post {
        always { 
          sh script: "docker rm fitnesse-firefox", returnStatus: true
          dir("f4a/FitNesseForAppian"){ junit "fitnesse-results.xml" }
        }
        failure {
          script {
            def jenkinsUtils = load "groovy/JenkinsUtils.groovy"
            archiveArtifacts artifacts: jenkinsUtils.retrieveLogs("fitnesse-automation.acceptance.properties"), fingerprint: true
          }
        }
      }
    }
    stage("Create Application Release") {
      steps {
        script {
          echo 'Create Application Release'
        }
      }
    }
    stage("Promotion Decision") {
      steps {
        input "Deploy to Production?"
      }
    }
    stage("Deploy to Production") {
      steps {
        script {
          echo 'Create Application Release'
        }
      }
    }
    stage("Tag Successful Import into Production") {
      steps {
        script {
          echo 'Tag Successful Import into Production'
        }
      }
    }
  }
  
}
