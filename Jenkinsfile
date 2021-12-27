pipeline {
  agent any
  parameters {
      choice(name: 'CHOICE', choices: ['mvn', 'gradle'], description: 'Pick something')
  }
  stages {
      stage('Build') {
        steps {
          sh "mvn -version"
//           sh "\"${params.CHOICE}\" -version"
//           sh 'echo "Hello World"'
//           sh '''
//               echo "Multiline script"
//               java -version
//               mvn -version
//               cd SpringMVC
//               mvn clean compile
//               ls
//               '''
        }
    }
  }
}