pipeline {
    agent any
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
                sh 'rm -d -f JavaMentornshipTasks'
                sh 'git clone https://github.com/pashtet02/JavaMentornshipTasks.git'
                sh 'cd JavaMentornshipTasks'
                sh 'cd SpringMvc'
                sh 'mvn -Dmaven.test.failure.ignore=true package'
            }
            post {
                success {
                    junit 'target/surefire-reports/**/*.xml'
                }
            }
        }
    }
}