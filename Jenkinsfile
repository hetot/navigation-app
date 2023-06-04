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
    }
}