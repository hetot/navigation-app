pipeline {
    agent any
    stages {
        stage('Package') {
            steps {
                sh 'mvn package'
            }
        }
        stage("Docker build") {
            steps {
                sh 'docker build -t hetot/navigation-app .'
            }
        }
        stage("Docker push") {
            steps {
                withCredentials([string(credentialsId: 'docker-hub-pwd', variable: 'dockerhubpwd')]) {
                    sh 'docker login -u hetot -p ${dockerhubpwd}'
                }
                sh 'docker push hetot/navigation-app'
            }
        }
    }
}