pipeline {
  agent any
  stages {
      stage('Build') {
        steps {
          sh 'echo "Hello World"'
          sh '''
              echo "Multiline script"
              java -version
              cd SpringMVC
              mvn clean compile
              ls
              '''
        }
    }
  }
}
