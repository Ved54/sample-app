pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/ved54/sample-app.git'
            }
        }

        stage('Build') {
            steps {
                echo 'Building the application...'
                sh 'javac HelloWorld.java'  // Example for Java app
            }
        }

        stage('Test') {
            steps {
                echo 'Running tests...'
                sh 'java HelloWorldTest' // Replace with your test command
            }
        }

        stage('Deploy') {
            steps {
                echo 'Deploying to AWS EC2...'
                // Simple simulation of deployment
                sh 'scp -i /path/to/key.pem HelloWorld.class ubuntu@<EC2-IP>:/var/www/app/'
            }
        }
    }

    post {
        success {
            echo 'Pipeline executed successfully!'
        }
        failure {
            echo 'Pipeline failed. Please check logs.'
        }
    }
}

