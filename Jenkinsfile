pipeline {
    agent any

    tools {
        maven 'Maven'
    }

    environment {
        DOCKER_IMAGE = 'rutul2108/devops_spe:latest'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Docker Build') {
            steps {
                sh "docker build -t ${DOCKER_IMAGE} ."
            }
        }

        stage('Docker Push') {
            steps {
                withDockerRegistry([credentialsId: 'dockerhub-cred', url: '']) {
                    sh "docker push ${DOCKER_IMAGE}"
                }
            }
        }
    }

    post {
        always {
            echo 'Pipeline completed.'
        }
        success {
            echo 'Build & Push successful ✅'
        }
        failure {
            echo 'Pipeline failed ❌'
        }
    }
}