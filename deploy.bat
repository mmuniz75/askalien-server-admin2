
set DATABASE_URL=postgres://postgres:postgres@localhost:5432/mythidb
set APPLICATION_WEBSITE=*
set AUTHENTICATOR_SERVER=https://cognito-idp.us-east-1.amazonaws.com/us-east-1_vXjuddh4O

rem heroku login
mvn clean spring-boot:build-image
heroku container:login
heroku container:push web -a askalien-admin2
heroku container:release web --app askalien-admin2