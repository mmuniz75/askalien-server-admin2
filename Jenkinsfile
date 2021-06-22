pipeline {
   agent any

   environment {
      HEROKU_API_KEY = credentials('HEROKU_API_KEY')
   }

   stages {
      stage('Checkout') {
         steps {
           git(url: 'https://github.com/mmuniz75/askalien-server-admin2',
               branch: "${branch}")
         }
      }
      stage('Build image') {
         steps {
            sh 'mvn clean spring-boot:build-image'
         }
      }
      stage('Push Heroku') {
         steps {
           sh 'heroku container:login'
           sh "heroku container:push web -a askalien-admin2"
         }
      }
       stage('Release Heroku') {
           steps {
              sh 'heroku container:release web --app askalien-admin2'
           }
        }
   }
}

