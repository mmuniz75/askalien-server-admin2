export DATABASE_URL=postgres://postgres:postgres@localhost:5432/mythidb
export APPLICATION_WEBSITE=*
export AUTHENTICATOR_SERVER=https://cognito-idp.us-east-1.amazonaws.com/us-east-1_vXjuddh4O

#sudo update-alternatives --config java

mvn clean spring-boot:build-image
docker tag askalien-admin:4.1.1 mmuniz/askalien-admin:4.1.1
docker push mmuniz/askalien-admin:4.1.1
/home/muniz/.fly/bin/flyctl deploy