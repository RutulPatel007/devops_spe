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
                echo "✅ Build & Push successful!"
                emailext(
                    subject: "✅ Jenkins Build Successful: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                    body: """
                    <h2>Build Successful 🎉</h2>
                    <p><b>Project:</b> ${env.JOB_NAME}</p>
                    <p><b>Build Number:</b> ${env.BUILD_NUMBER}</p>
                    <p><b>Status:</b> SUCCESS ✅</p>
                    <p><b>Git Commit:</b> ${env.GIT_COMMIT}</p>
                    <p><b>Build URL:</b> <a href="${env.BUILD_URL}">${env.BUILD_URL}</a></p>
                    """,
                    to: 'rutul2108@gmail.com'
                )
            }


            failure {
                echo "❌ Pipeline failed!"
                emailext(
                    subject: "❌ Jenkins Build Failed: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                    body: """
                    <h2>Build Failed ❌</h2>
                    <p><b>Project:</b> ${env.JOB_NAME}</p>
                    <p><b>Build Number:</b> ${env.BUILD_NUMBER}</p>
                    <p><b>Status:</b> FAILURE</p>
                    <p><b>Build URL:</b> <a href="${env.BUILD_URL}">${env.BUILD_URL}</a></p>
                    """,
                    to: 'rutul2108@gmail.com'
                )
            }

            always {
                echo "📨 Notification sent. Pipeline completed."
            }
        }
}