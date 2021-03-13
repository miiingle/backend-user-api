# Database Migration

- dockerize liquibase
```shell
docker build -t miiingle-user-api-liquibase:latest .
docker build -t 327229172692.dkr.ecr.us-east-1.amazonaws.com/miiingle.net.misc:user-api-liquibase .
docker push 327229172692.dkr.ecr.us-east-1.amazonaws.com/miiingle.net.misc:user-api-liquibase
```