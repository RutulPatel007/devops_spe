pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/RutulPatel007/devops_spe.git'
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
                sh 'docker build -t rutul2108/devops_spe:latest .'
            }
        }
        stage('Docker Push') {
            steps {
                withDockerRegistry([credentialsId: 'dockerhub-cred', url: '']) {
                    sh 'docker push rutul2108/devops_spe:latest'
                }
            }
        }
    }
}