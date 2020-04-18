pipeline {
  agent any
  tools {
        // Note: this should match with the tool name configured in your jenkins instance (JENKINS_URL/configureTools/)
        gradle "Gradle 6.4"
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
        
        // Rule Test Reports file path
        RULE_TEST_REPORTS_PATH = "devops/rule_testing/reports"
        
        GATLING_HOME ="/var/tmp/gatling3/gatling-charts-highcharts-bundle-3.0.3"
        
        // SSH Docker
        REMOTE_DOCKER_HOST = "ssh://appian@appian-1.appiancorp.com"
        REMOTE_DOCKER_HOST_CREDENTIALS = credentials('ssh-credentials')
        
    }
   
    
    
   
   
 stages {
  
  stage ('Main Stage') {
        steps {
  	script{
  	stage("Connect with remote docker host") {
      
        def remote = [:]
    remote.name = 'appian-1'
    remote.host = 'appian-1.appiancorp.com'
    remote.user = ${REMOTE_DOCKER_HOST_CREDENTIALS_USR}
    remote.password = ${REMOTE_DOCKER_HOST_CREDENTIALS_PSW}
    remote.allowAnyHosts = true
  
        sshCommand remote: remote, command: "ls -lrt"
          // Run docker-compose on remote host
          sh "docker-compose -f /productos/appian-docker up -d"
          
          }
          }
    }
    }
 	}
  
}
