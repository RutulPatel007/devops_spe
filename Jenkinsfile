pipeline {
    agent any

    environment {
        DOCKER_IMAGE = 'rutul2108/devops_spe:latest'
        MAVEN_HOME = '/opt/homebrew/bin'   // 👈 Change this to your actual mvn path
        PATH = "$PATH:${MAVEN_HOME}"
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                sh '''
                echo "Using Maven from: $(which mvn)"
                mvn clean package
                '''
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