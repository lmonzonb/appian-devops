pipeline {
  agent any
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
        
        // Appian Application to deploy
        APPLICATIONNAME = "LMB_FF"
        // PATH of Appian Application to deploy
        APPLICATION_PATH = "appian/applications/LMB_FF/app-package.zip"
        
        // Username of the Appian user account
        SITEUSERNAME = "user"
        // Password of the Appian user account
        SITEPASSWORD = "pass"
    }
    options {
    skipDefaultCheckout true
  }

    
   
  stages {
  	stage('clean_workspace_and_checkout_source') {
      steps {
        deleteDir()
        checkout scm
      }
    }
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
            
            // JUST FOR TESTING PURPOSES
            jenkinsUtils.setProperty("import-manager.properties", "application_path", "${APPLICATION_PATH}")
            sh "ls appian/applications/LMB_FF"
            
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
    stage("Run Integration Tests") {
      steps {
        script {
          echo 'Run Integration Tests'
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
          echo 'Run Acceptance Tests'
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
