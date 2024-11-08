pipeline {
    agent any

//     environment {
//         CRED_ID = 'tomcat'
//         PATH = ''
//         URL = 'http://172.21.0.2:8080'
//     }

    tools {
        maven 'maven'
        jdk 'jdk'
    }

    stages {
        //Skip stage checkout
        //stage('Checkout') {}

        stage ('Build') {
            steps {
                echo '>>> Build'
                sh 'mvn clean install'
                echo '>>> Debug'
                sh 'ls -lrt'
            }
            post {
                success {
                    echo '>>> Archive Artifacts'
                    archiveArtifacts artifacts: '**/target/*.war'
                }
            }
        }

        stage ('Test') {
            steps {
                echo '>>> Test'
            }
        }

        stage ('Deploy') {
            steps {
                echo '>>> Deploy'
                deploy adapters: [tomcat9(credentialsId: 'tomcat', path: '', url: 'http://172.18.0.4:8080')], contextPath: '/api', war: '**/*.war'
            }
            post {
                success {
                    input message: 'Back up?'
                    deploy adapters: [tomcat9(credentialsId: 'tomcat', path: '', url: 'http://172.18.0.6:8080')], contextPath: '/api', war: '**/*.war'
                }
            }
        }

    }
}