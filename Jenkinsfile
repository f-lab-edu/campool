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
            post {
                success {
                    echo 'build success!!'
                }
                failure {
                    echo 'build failure!!'
                }
            }
        }

        stage('Archive') {
            steps {
                archiveArtifacts 'target/*.jar'
            }
        }

        stage('Deploy') {
            steps {
                sh 'mv target/*.jar .'
                sshPublisher(
                    continueOnError: false,
                    failOnError: true,
                    publishers: [
                        sshPublisherDesc(
                            configName: "Web001",
                            verbose: true,
                            transfers: [
                                sshTransfer(
                                    sourceFiles: "campool*.jar",
                                    execCommand: "sh /deploy/restart_server.sh"
                                )
                            ]
                        )
                    ]
                )
            }
        }
    }
}
