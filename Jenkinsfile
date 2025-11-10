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
                // compile and put .class in current workspace
                sh 'javac HelloWorld.java'
                sh 'ls -la' // show produced files for debugging
            }
        }

        stage('Test') {
            steps {
                echo 'Running tests...'
                // run test from current workspace; test must exit non-zero on failure
                sh 'java -cp . HelloWorldTest'
            }
        }

        stage('Prepare Artifact') {
            steps {
                echo 'Preparing artifact for deployment...'
                // ensure the class file exists
                sh 'if [ -f HelloWorld.class ]; then echo "Artifact ready"; else echo "Artifact missing!" && exit 1; fi'
            }
        }

        stage('Deploy') {
            steps {
                echo 'Deploying to AWS EC2...'
                
                sh '''
                   chmod 400 /var/lib/jenkins/aws/my-ec2-key.pem || true
                   scp -o StrictHostKeyChecking=no -i /var/lib/jenkins/aws/yourkey.pem HelloWorld.class ubuntu@13.235.67.84:/var/www/app/
                '''
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
