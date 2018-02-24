#!/bin/sh

echo "********************************************************"
echo "Waiting for the Discovery Service to start on port $DISCOVERYSERVICE_PORT"
echo "********************************************************"
while ! `nc -z discovery-service $DISCOVERYSERVICE_PORT`; do sleep 3; done
echo "******* Discovery Service has started"

echo "********************************************************"
echo "waiting for Currency service starting on port $CURRENCYSERVICE_PORT"
echo "********************************************************"
while ! `nc -z currencyservice $CURRENCYSERVICE_PORT`; do sleep 3; done
echo ">>>>>>>>>>>> Currency service has started"

echo "********************************************************"
echo "Starting Rates Collector Service";
java -Deureka.client.serviceUrl.defaultZone=$DISCOVERYSERVICE_URI           \
     -Dspring.profiles.active=$PROFILE  -jar /usr/local/ratescollector/@project.build.finalName@.jar

echo ">>>>>>>>>>>> Rates Collector Service started!"

