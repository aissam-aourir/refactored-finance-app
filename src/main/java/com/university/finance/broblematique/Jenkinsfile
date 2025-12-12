pipeline {
    agent any
    
    tools {
        maven 'Maven_3.8'
        jdk 'JDK_11'
    }
    
    stages {
        stage('Checkout') {
            steps {
                git branch: 'main',
         url: 'https://github.com/ENSAM/finance-refactoring.git'
            }
        }
        
        stage('Build & Tests') {
            steps {
                sh 'mvn clean compile test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
        
        stage('Code Quality') {
            steps {
                sh 'mvn sonar:sonar'
            }
        }
        
        stage('Packaging') {
            steps {
                sh 'mvn package -DskipTests'
            }
        }
    }
    
    post {
        always {
            emailext attachLog: true,
                subject: "Build ${currentBuild.result}: ${JOB_NAME}",
                to: 'professor@university.edu',
                body: "Build ${currentBuild.result}\n${env.BUILD_URL}"
        }
    }
}
