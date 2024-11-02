pipeline {
    agent any

    environment {
        CRED_ID = 'tomcat'
        PATH = ''
        URL = 'http://172.21.0.2:8080'
    }

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
                sh 'mvn clean package'
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
                deploy adapters: [tomcat9(credentialsId: '$CRED_ID', path: '$PATH', url: '$URL')], contextPath: null, war: '**/*.war'
            }
        }

    }
}