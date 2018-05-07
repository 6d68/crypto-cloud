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
echo "Waiting for Configuration Service starting on port $(getPort $CONFIGSERVER_PORT)"
echo "********************************************************"
while ! `nc -z configurationservice $(getPort $CONFIGSERVER_PORT)`; do sleep 3; done
echo ">>>>>>>>>>>> Configuration Service has started"

echo "********************************************************"
echo "Starting API Gateway"
echo "********************************************************"
java -Deureka.client.serviceUrl.defaultZone=$DISCOVERYSERVICE_URI           \
     -Dspring.cloud.config.uri=$CONFIGSERVER_URI                            \
     -Dspring.profiles.active=$PROFILE  -jar /usr/local/apigateway/@project.build.finalName@.jar

echo ">>>>>>>>>>>> API Gateway started!"