pipeline {
  agent any
  parameters {
      choice(name: 'CHOICE', choices: ['One', 'Two', 'Three'], description: 'Pick something')
      string(name: 'PERSON', defaultValue: 'Mr Jenkins', description: 'Who should I say hello to?')
  }
  stages {
      stage('Build') {
        steps {
        echo "Hello ${params.PERSON}"
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
