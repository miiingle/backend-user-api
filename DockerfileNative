FROM oracle/graalvm-ce:20.3.0-java11 AS graalvm
RUN gu install native-image
WORKDIR /home/app
COPY build/layers/libs /home/app/libs
COPY build/layers/resources /home/app/resources
COPY build/layers/application.jar /home/app/application.jar
RUN native-image -H:Class=net.miiingle.user.api.Application -H:Name=application --no-fallback -cp /home/app/libs/*.jar:/home/app/resources:/home/app/application.jar

FROM amazonlinux
RUN yum update -y && yum install -y gcc gcc-c++ libc6-dev  zlib1g-dev curl bash zlib zlib-devel zip tar
COPY --from=graalvm /home/app/application /app/application
ENTRYPOINT ["/app/application"]