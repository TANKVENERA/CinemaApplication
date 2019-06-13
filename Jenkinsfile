pipeline {
 agent any
 stages {
   stage('==BUILD==') {
     steps {
       sh "rm -rf CinemaApplication"
       sh "git clone https://github.com/TANKVENERA/CinemaApplication.git"
       sh "mvn clean install -DskipTests"
     }
   }
   stage('==RUN TESTS==') {
    steps {
      sh "mvn test -f CinemaApplication"
    }
    }
 }
}