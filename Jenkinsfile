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
        stage('Registry Docker image') {
            steps {
              sh 'docker tag askalien-admin:4.1.1 mmuniz/askalien-admin:4.1.1'
              sh 'docker push mmuniz/askalien-admin:4.1.1'
            }
         }
       stage('Login fly.io') {
          steps {
            sh 'flyctl auth login'
          }
       }
        stage('Push fly.io') {
            steps {
               sh 'flyctl deploy'
            }
         }
   }
}

