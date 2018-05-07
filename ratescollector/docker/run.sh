#!/bin/sh
getPort() {
    echo $1 | cut -d : -f 3 | xargs basename
}

echo "********************************************************"
echo "Waiting for the Discovery Service to start on port $(getPort $DISCOVERYSERVICE_PORT)"
echo "********************************************************"
while ! `nc -z discovery-service $(getPort $DISCOVERYSERVICE_PORT)`; do sleep 3; done
echo "******* Discovery Service has started"

echo "********************************************************"
echo "waiting for Currency service starting on port $(getPort $CURRENCYSERVICE_PORT)"
echo "********************************************************"
while ! `nc -z currencyservice $(getPort $CURRENCYSERVICE_PORT)`; do sleep 3; done
echo ">>>>>>>>>>>> Currency service has started"

echo "********************************************************"
echo "Starting Rates Collector Service";
java -Deureka.client.serviceUrl.defaultZone=$DISCOVERYSERVICE_URI           \
     -Dspring.profiles.active=$PROFILE  -jar /usr/local/ratescollector/@project.build.finalName@.jar

echo ">>>>>>>>>>>> Rates Collector Service started!"

