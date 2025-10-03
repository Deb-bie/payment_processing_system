pipeline {
    agent any

    environment {

    }

    stages {
        stage('Checkout') {
            steps {
                echo 'Checkout.....'
                git branch: 'main', url: 'https://github.com/Deb-bie/payment_processing_system.git'
            }
        }

        stage('Bulld & Test') {
            steps {
                echo 'Building...'
                script {
                    sh './gradlew clean build'
                }
            }
        }

        stage('Docker Build') {
            steps {
                script {
                    // build docker images
                }
            }
        }
    }

    post {
        always {
            // clean up workspace
            cleanWs()
        }
        success {
            echo 'Build and deployment successful!'
        }
        failure {
            echo "Pipeline failed. Check ogs for erros."
        }
    }
}