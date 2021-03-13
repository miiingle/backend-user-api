# Load Testing

- build the image
```shell
docker build -t miiingle.net.misc:latest 327229172692.dkr.ecr.us-east-1.amazonaws.com/miiingle.net.misc:jmeter-user-api
```


- test locally
```shell
jmeter -n -t PerformanceTest.jmx \
-Jprotocol=http \
-Jhost=localhost \
-Jport=8080 \
-Jusers=1000 \
-JrampUp=100 \
-Jloops=1000000
```
