FROM amazoncorretto:11 as builder
COPY . /home
WORKDIR /home
RUN ./gradlew build -x test

FROM amazoncorretto:11
WORKDIR /home/app
COPY --from=builder home/build/layers/libs /home/app/libs
COPY --from=builder home/build/layers/resources /home/app/resources
COPY --from=builder home/build/layers/application.jar /home/app/application.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/home/app/application.jar"]