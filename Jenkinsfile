pipeline {
    agent any
    parameters {
          choice(name: 'CHOICE', choices: ['mvn', 'gradle'], description: 'Pick something')
      }
    tools {
        maven 'Maven'
    }
    stages {
        stage ('Build') {
            steps {
                sh '''ls -la
                cd JavaMentornshipTasks/SpringMVC
                ls -la
                mvn -Dmaven.test.failure.ignore=true install'''
            }
        }
    }
}