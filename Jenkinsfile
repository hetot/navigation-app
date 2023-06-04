pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/hetot/navigation-app', branch: 'master'
            }
        }
        stage('Package') {
            steps {
                sh 'mvn package'
            }
        }
        stage("Docker build") {
            steps {
                'docker build -t hetot/navigation-app .'
            }
        }
    }
}