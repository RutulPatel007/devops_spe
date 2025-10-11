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

        stage('Docker Build & Push') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'docker-creds', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                    sh '''
                    echo "$DOCKER_PASS" | docker login -u "$DOCKER_USER" --password-stdin
                    docker build -t ${DOCKER_IMAGE} .
                    docker push ${DOCKER_IMAGE}
                    docker logout
                    '''
                }
            }
        }

    }

    post {

            success {
                mail to: 'rutul2108@gmail.com',
                     subject: "Build Success: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                     body: "Your Jenkins build was successful!\n${env.BUILD_URL}"
            }

            failure {
                echo "❌ Pipeline failed!"
                mail to: 'rutul2108@gmail.com',
                     subject: "Build Success: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                     body: "Your Jenkins build was successful!\n${env.BUILD_URL}"
            }
            always {
                echo "📨 Notification sent. Pipeline completed."
            }
        }
}