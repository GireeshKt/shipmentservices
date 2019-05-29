#!groovy
def TARGET_HOST = 'localhost'
def VERSION = '0.0.0'
def SSH_ARGS = '-oStrictHostKeyChecking=no -oServerAliveInterval=60'
def JAR_URL = 'http://localhost:8080/repository/com/blumeglobal/visibility/0.0.0/shipmentmanagement-0.0.1-SNAPSHOT.jar'
pipeline {
    agent none
    environment {
        NEXUS_REPO = credentials('nexus-repo-credentials')
        GITHUB_AUTH_TOKEN = credentials('jx-release-version-github-token')
        GITHUB_REPO = 'visibility'
        GCP_CONTAINER_SECRET = credentials('gcp-blume-shared-infra-dev-container-registry')
        GCP_CONTAINER_REGISTRY = 'blume-shared-infra-dev'
      	GCP_CONTAINER_SECRET_PROD = credentials('gcp-blume-shared-infra-prod-container-registry')
		GCP_CONTAINER_REGISTRY_PROD = 'blume-shared-infra-prod'

    }
    options {
        skipDefaultCheckout()
        timeout(time: 1, unit: 'HOURS')
    }
    stages {
        stage('CI Build') {
            agent {
                label 'linux&&jdk8'
            }
            when {
                not {
                    branch 'master'
                }
            }
            steps {
                checkout scm
                script {
                    VERSION = sh returnStdout: true,
                            script: "docker run --rm -v\$(pwd):/git -eGITHUB_AUTH_TOKEN=${GITHUB_AUTH_TOKEN} " +
                                    "blumeglobal/jx-release-version --gh-owner=rez-one --gh-repository=${GITHUB_REPO}"
                }
                sh "./gradlew clean build publish -P nexusUsername=${NEXUS_REPO_USR} -P nexusPassword=${NEXUS_REPO_PSW} " +
                        "-P version=${VERSION}-SNAPSHOT"
                milestone label: 'build', ordinal: 1
                
                script {
                	
                    def jobName = "${JOB_NAME}"
                    
                    jobNameParts = jobName.split('/')
                    
					def jenkinsJobName = jobNameParts[jobNameParts.length - 1].toLowerCase().replaceAll(" ","-").replaceAll("_","-")
                    
                    DOCKER_LOGIN = sh returnStdout: true, script: "docker login -u _json_key -p \"\${GCP_CONTAINER_SECRET}\" https://us.gcr.io"
                    		    		
			    	docker.withRegistry('https://us.gcr.io') {
			    	    
                		def customImage = docker.build("${GCP_CONTAINER_REGISTRY}/${GITHUB_REPO}:${jenkinsJobName}-${BUILD_NUMBER}")
                
        			    customImage.push()
        			}
                }
            }
        }
        stage('Release Build') {
            agent {
                label 'linux&&jdk8'
            }
            when {
                branch 'master'
            }
            steps {
                checkout scm
                script {
                    VERSION = sh returnStdout: true,
                            script: "docker run --rm -v\$(pwd):/git -eGITHUB_AUTH_TOKEN=${GITHUB_AUTH_TOKEN} " +
                                    "blumeglobal/jx-release-version --gh-owner=rez-one --gh-repository=${GITHUB_REPO}"
                }
                sh "./gradlew clean build publish -P nexusUsername=${NEXUS_REPO_USR} -P nexusPassword=${NEXUS_REPO_PSW} " +
                        "-P version=${VERSION}"
                sh "git tag -a v${VERSION} -m 'Release Version ${VERSION}'"
                sh "git remote set-url origin git@github.com:rez-one/${GITHUB_REPO}.git"
                sshagent(['jenkins-worker-ssh-key']) {
                    sh "git push origin v${VERSION}"
                }
                milestone label: 'build', ordinal: 2
                
                script {
                    
                    DOCKER_LOGIN = sh returnStdout: true, script: "docker login -u _json_key -p \"\${GCP_CONTAINER_SECRET_PROD}\" https://us.gcr.io"
                    		    		
			    	docker.withRegistry('https://us.gcr.io') {
			    	    
                		def customImage = docker.build("${GCP_CONTAINER_REGISTRY_PROD}/${GITHUB_REPO}:${VERSION}")
                	   
        			    customImage.push()
        			}
                }
            }
        }    
    }
}
