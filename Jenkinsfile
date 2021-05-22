pipeline {
    agent any
    tools {
        maven "M3"
    }
    environment {
        private_registry = 'http://10.41.4.197:5000'
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

        stage('Push Docker Image') {
            steps {
                script {
                    docker.withRegistry(private_registry) {
                        docker.build('campool-web-server').push('latest')
                    }
                }
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
                                    sourceFiles: "",
                                    execCommand: "sh /deploy/pull_and_restart_server.sh"
                                )
                            ]
                        )
                    ]
                )
            }
        }
    }
}
