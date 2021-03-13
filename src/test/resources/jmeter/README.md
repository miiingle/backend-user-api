jmeter -n -t PerformanceTest.jmx \
-Jprotocol=http \
-Jhost=localhost \
-Jport=8080 \
-Jusers=1000 \
-JrampUp=100 \
-Jloops=1000000