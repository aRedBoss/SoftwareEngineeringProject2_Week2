pipeline {
    agent any

    tools {
        maven 'MAVEN3'
    }

    environment {
        DOCKER_IMAGE = 'omarald/inclassassignment1_project2'
        DOCKER_TAG = 'latest'
    }

    stages {
        stage('checking') {
            steps {
                git branch: 'main', url: 'https://github.com/aRedBoss/SoftwareEngineeringProject2_Week3.git'
            }
        }

        stage('build') {
            steps {
                bat 'mvn clean install'
            }
        }

        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }

        stage('Code Coverage') {
            steps {
                bat 'mvn jacoco:report'
            }
        }

        stage('Publish Test Results') {
            steps {
                junit '**/target/surefire-reports/*.xml'
            }
        }

        stage('Publish Coverage Report') {
            steps {
                jacoco()
            }
        }

        stage('Docker Build & Push') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: 'DOCKERHUB',
                    usernameVariable: 'DOCKERHUB_USER',
                    passwordVariable: 'DOCKERHUB_PASS'
                )]) {
                    bat '''
                        docker build -t %DOCKER_IMAGE%:%DOCKER_TAG% .
                        echo %DOCKERHUB_PASS% | docker login -u %DOCKERHUB_USER% --password-stdin
                        docker push %DOCKER_IMAGE%:%DOCKER_TAG%
                        docker logout
                    '''
                }
            }
        }
    }

    post {
        always {
            echo 'Pipeline finished.'
        }
    }
}
