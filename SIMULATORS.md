# Images to Simulate Other Services
We keep a copy of the dependent image, so we don't have to rely on other image repos


### Pull and Re-tag
```shell
docker pull $CONTAINER_REGISTRY_HOST/miiingle.net.misc:keycloak-simulator-latest
docker tag $CONTAINER_REGISTRY_HOST/miiingle.net.misc:keycloak-simulator-latest miiingle.net.misc:keycloak-simulator-latest

docker pull $CONTAINER_REGISTRY_HOST/miiingle.net.misc:rds-simulator-latest
docker tag $CONTAINER_REGISTRY_HOST/miiingle.net.misc:rds-simulator-latest miiingle.net.misc:rds-simulator-latest

docker pull $CONTAINER_REGISTRY_HOST/miiingle.net.misc:zipkin-simulator-latest
docker tag $CONTAINER_REGISTRY_HOST/miiingle.net.misc:zipkin-simulator-latest miiingle.net.misc:zipkin-simulator-latest

docker pull $CONTAINER_REGISTRY_HOST/miiingle.net.misc:liquibase-latest
docker tag $CONTAINER_REGISTRY_HOST/miiingle.net.misc:liquibase-latest miiingle.net.misc:liquibase-latest
```

### Keycloak
```shell
docker pull quay.io/keycloak/keycloak:12.0.4
docker tag quay.io/keycloak/keycloak:12.0.4 $CONTAINER_REGISTRY_HOST/miiingle.net.misc:keycloak-simulator-latest
docker tag quay.io/keycloak/keycloak:12.0.4 miiingle.net.misc:keycloak-simulator-latest

docker push $CONTAINER_REGISTRY_HOST/miiingle.net.misc:keycloak-simulator-latest
```

### RDS
```shell
docker pull postgres:11.10-alpine
docker tag postgres:11.10-alpine $CONTAINER_REGISTRY_HOST/miiingle.net.misc:rds-simulator-latest
docker tag postgres:11.10-alpine miiingle.net.misc:rds-simulator-latest

docker push $CONTAINER_REGISTRY_HOST/miiingle.net.misc:rds-simulator-latest
```

### Zipkin
```shell
docker pull openzipkin/zipkin:latest
docker tag openzipkin/zipkin:latest $CONTAINER_REGISTRY_HOST/miiingle.net.misc:zipkin-simulator-latest
docker tag openzipkin/zipkin:latest miiingle.net.misc:zipkin-simulator-latest

docker push $CONTAINER_REGISTRY_HOST/miiingle.net.misc:zipkin-simulator-latest
```

### Liquibase (Base Image)
```shell
docker pull liquibase/liquibase
docker tag liquibase/liquibase $CONTAINER_REGISTRY_HOST/miiingle.net.misc:liquibase-latest
docker tag liquibase/liquibase miiingle.net.misc:liquibase-latest

docker push $CONTAINER_REGISTRY_HOST/miiingle.net.misc:liquibase-latest
```