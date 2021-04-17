pipeline {
    agent any
    tools {
        maven "M3"
    }

    stages {
        stage('Check out') {
            steps {
                checkout scm
            }
        }

        stage('Unit Test') {
            steps {
                sh "mvn test"
            }
        }

        stage('Build') {
            steps {
                sh "mvn clean package -DskipTests"
            }
        }

        stage('Archive') {
            steps {
                archiveArtifacts 'target/*.jar'
            }
        }
    }
}
