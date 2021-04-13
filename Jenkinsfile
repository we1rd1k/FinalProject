pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                echo 'Building..'
                git 'https://github.com/we1rd1k/FinalProject.git'
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
                sh 'mvn clean test -Dtest=ru.innopolis.at.runner.TestRunner.java'
            }
        }
    }
    post {
        always {
            allure includeProperties: false, jdk: '', results: [[path: 'target/allure-results']]
        }
    }
}
