pipeline {
  agent any
  tools {
        // Note: this should match with the tool name configured in your jenkins instance (JENKINS_URL/configureTools/)
        gradle "Gradle 6.4"
    }
  environment {
       
        
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
    //remote.user = '${REMOTE_DOCKER_HOST_CREDENTIALS_USR}'
    //remote.password = '${REMOTE_DOCKER_HOST_CREDENTIALS_PSW}'
    remote.allowAnyHosts = true
    
    withCredentials([sshUserPrivateKey(credentialsId: 'ssh-key-credentials', keyFileVariable: 'identity', passphraseVariable: '', usernameVariable: 'userName')]) {
    	remote.user = userName
        remote.identityFile = identity
        
        // Run docker-compose on remote host
        sshCommand remote: remote, command: "docker-compose -f /productos/appian-docker/docker-compose.yml up -d"
	}
       
          
          }
          }
    }
    }
 	}
  
}
