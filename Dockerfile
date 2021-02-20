#FROM amazonlinux as graalvm
#ENV LANG=en_US.UTF-8
#ENV GRAAL_VERSION 20.1.0
#ENV JDK_VERSION java11
#ENV GRAAL_FILENAME graalvm-ce-${JDK_VERSION}-linux-amd64-${GRAAL_VERSION}.tar.gz
#RUN yum install -y gcc gcc-c++ libc6-dev  zlib1g-dev curl bash zlib zlib-devel zip tar
#RUN curl -4 -L https://github.com/graalvm/graalvm-ce-builds/releases/download/vm-${GRAAL_VERSION}/${GRAAL_FILENAME} -o /tmp/${GRAAL_FILENAME}
#RUN tar -zxvf /tmp/${GRAAL_FILENAME} -C /tmp
#RUN mv /tmp/graalvm-ce-${JDK_VERSION}-${GRAAL_VERSION} /usr/lib/graalvm
#RUN rm -rf /tmp/*
#CMD ["/usr/lib/graalvm/bin/native-image"]

#FROM graalvm
#COPY --from=builder /home/application/ /home/application/
#WORKDIR /home/application
#RUN /usr/lib/graalvm/bin/gu install native-image
#RUN /usr/lib/graalvm/bin/native-image --no-server -cp build/libs/complete-*-all.jar
#RUN chmod 777 bootstrap
#RUN chmod 777 complete
#RUN zip -j function.zip bootstrap complete
#EXPOSE 8080
#ENTRYPOINT ["/home/application/complete"]

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