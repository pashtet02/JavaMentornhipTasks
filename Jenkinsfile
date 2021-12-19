pipeline {
  agent any
  parameters {
      string(name: 'Builder', defaultValue: 'mvn', description: 'How should I build the project?')
  }
  stages {
      stage('Build') {
        steps {
          sh 'echo "Hello World"'
          sh '''
              echo "Multiline script"
              java -version
              mvn -version
              cd SpringMVC
              sh "${params.Builder} -version"
              mvn clean compile
              ls
              '''
        }
    }
  }
}
