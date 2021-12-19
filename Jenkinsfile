pipeline {
  agent any
  stages {
      stage('Build') {
        steps {
          sh 'echo "Hello World"'
          sh '''
              echo "Multiline script"
              mvn clean compile
              ls
              '''
        }
        stage('Test') {
          steps {
            sh 'echo "Test World"'
            }
    }
  }
}
