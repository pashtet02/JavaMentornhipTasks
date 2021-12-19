pipeline {
  agent any
  parameters {
      choice(name: 'CHOICE', choices: ['One', 'Two', 'Three'], description: 'Pick something')
  }
  stages {
      stage('Build') {
        steps {
          sh "echo \"${params.CHOICE}\""
          sh 'echo "Hello World"'
          sh '''
              echo "Multiline script"
              java -version
              mvn -version
              cd SpringMVC
              mvn clean compile
              ls
              '''
        }
    }
  }
}
