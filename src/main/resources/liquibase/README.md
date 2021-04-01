# Database Migration

- dockerize liquibase
```shell
docker build -t miiingle.net.misc:liquibase-user-api-latest src/main/resources/liquibase
docker tag miiingle.net.misc:liquibase-user-api-latest $CONTAINER_REGISTRY_HOST/miiingle.net.misc:liquibase-user-api-latest
docker push $CONTAINER_REGISTRY_HOST/miiingle.net.misc:liquibase-user-api-latest
```