while ! curl localhost:8081/auth/realms/master --fail
do
  echo 'waiting for keycloak to start...will try again in 3 seconds'
  sleep 3
done