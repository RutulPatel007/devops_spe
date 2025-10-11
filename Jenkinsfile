pipeline {
    agent any

    environment {
        DOCKER_IMAGE = 'rutul2108/devops_spe:latest'
        PATH = "/opt/homebrew/bin:/usr/local/bin:$PATH"  // 👈 Add both Docker & Maven
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Verify Tools') {
            steps {
                sh '''
                echo "Maven Path: $(which mvn)"
                echo "Docker Path: $(which docker)"
                mvn -v
                docker -v
                '''
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