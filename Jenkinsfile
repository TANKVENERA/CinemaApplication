pipeline {
 agent any
 stages {
   stage('==BUILD==') {
     steps {
       sh "mvn clean"
     }
   }
   stage('==RUN TESTS==') {
    steps {
      sh "mvn test"
    }
    }
 }
}
