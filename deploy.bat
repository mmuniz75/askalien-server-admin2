
rem heroku login
mvn clean spring-boot:build-image
heroku container:login
heroku container:push web -a askalien-admin2
heroku container:release web --app askalien-admin2