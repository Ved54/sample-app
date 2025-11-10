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
        // Compile both HelloWorld.java and HelloWorldTest.java
        sh 'javac HelloWorld.java HelloWorldTest.java'
        sh 'ls -la'
    }
}

stage('Test') {
    steps {
        echo 'Running tests...'
        // Run the compiled test class
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
            
            # Copy the compiled file to EC2
            scp -o StrictHostKeyChecking=no -i /var/lib/jenkins/aws/my-ec2-key.pem HelloWorld.class ubuntu@13.235.67.84:/var/www/app/
            
            # Run the Java program on EC2 and print its output
            ssh -o StrictHostKeyChecking=no -i /var/lib/jenkins/aws/my-ec2-key.pem ubuntu@13.235.67.84 "cd /var/www/app && java HelloWorld"
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
