# User API Server
![Build Status](https://codebuild.us-east-1.amazonaws.com/badges?uuid=eyJlbmNyeXB0ZWREYXRhIjoiU0Q0SURIaGdzVXRmYUJ4ZDY3b1RtSFppWVZESSs0ejdROERPWjE3STdHUHVuQWI5c2lEZW00TzFDc1FJLzNzY1RlUUhSN1V2RmRZUTcwQ0NwS3FsV0RvPSIsIml2UGFyYW1ldGVyU3BlYyI6InlFMDRvcVhIM1VvQjI0L0giLCJtYXRlcmlhbFNldFNlcmlhbCI6MX0%3D&branch=master)


The backend server for processing user registrations, login, and user profile



# API Design Principles
- CQRS: lets separate modification commands from data queries
```
Commands: 

POST   <resource>/
PUT    <resource>/{id}
PATCH  <resource>/{id}


Queries:
GET    <resource>/
GET    <resource>/search/findBy{predicate}
GET    <resource>/count/

```


## Feature rxjava3 documentation

- [Micronaut RxJava 3 documentation](https://micronaut-projects.github.io/micronaut-rxjava3/snapshot/guide/index.html)

## Feature openapi documentation

- [Micronaut OpenAPI Support documentation](https://micronaut-projects.github.io/micronaut-openapi/latest/guide/index.html)

- [https://www.openapis.org](https://www.openapis.org)

## Feature liquibase documentation

- [Micronaut Liquibase Database Migration documentation](https://micronaut-projects.github.io/micronaut-liquibase/latest/guide/index.html)

- [https://www.liquibase.org/](https://www.liquibase.org/)

## Feature lombok documentation

- [Micronaut Project Lombok documentation](https://docs.micronaut.io/latest/guide/index.html#lombok)

- [https://projectlombok.org/features/all](https://projectlombok.org/features/all)

## Feature http-client documentation

- [Micronaut Micronaut HTTP Client documentation](https://docs.micronaut.io/latest/guide/index.html#httpClient)

## Feature testcontainers documentation

- [https://www.testcontainers.org/](https://www.testcontainers.org/)

## Feature security-jwt documentation

- [Micronaut Micronaut Security JWT documentation](https://micronaut-projects.github.io/micronaut-security/latest/guide/index.html)
