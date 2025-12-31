pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                sh "mvn clean package"
            }
            post {
                success {
                    echo 'Build successful'
                }
                failure {
                    echo 'Build failed'
                }
            }

        }
        stage('Test') {
            steps {
                sh "mvn test"
            }
            post {
                success {
                    echo 'Tests successful'
                }
                failure {
                    echo 'Tests failed'
                }
            }
        }
    }
}