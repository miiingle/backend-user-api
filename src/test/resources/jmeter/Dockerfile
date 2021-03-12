FROM adoptopenjdk/openjdk11-openj9:jdk-11.0.1.13-alpine-slim

ARG JMETER_VERSION="5.4.1"

RUN cd /opt \
 && wget https://mirrors.estointernet.in/apache//jmeter/binaries/apache-jmeter-${JMETER_VERSION}.tgz \
 && tar xzf apache-jmeter-${JMETER_VERSION}.tgz \
 && rm apache-jmeter-${JMETER_VERSION}.tgz

RUN ln -s /opt/apache-jmeter-${JMETER_VERSION}/bin/jmeter /usr/local/bin

COPY PerformanceTest.jmx PerformanceTest.jmx

CMD jmeter -n -t PerformanceTest.jmx -Jhost=host.docker.internal