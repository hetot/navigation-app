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
                sh 'docker push hetot/navigation-app'
            }
        }
    }
}