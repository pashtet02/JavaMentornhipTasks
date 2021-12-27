pipeline {
    agent any
    parameters {
          choice(name: 'CHOICE', choices: ['mvn', 'gradle'], description: 'Pick something')
      }
    tools {
        maven 'Maven'
    }
    stages {
        stage ('Initialize') {
            steps {
                sh '''
                    echo "PATH = ${PATH}"
                    echo "M2_HOME = ${M2_HOME}"
                '''
            }
        }

        stage ('Build') {
            steps {
                sh 'ls -la'
                sh 'cd JavaMentornshipTasks/SpringMVC'
                sh 'ls -la'
                sh 'mvn clean compile'
            }
            post {
                success {
                    junit 'target/surefire-reports/**/*.xml'
                }
            }
        }
    }
}