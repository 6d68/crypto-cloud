#!/bin/sh

echo "********************************************************"
echo "Waiting for the Discovery Service to start on port $DISCOVERYSERVICE_PORT"
echo "********************************************************"
while ! `nc -z discovery-service  $DISCOVERYSERVICE_PORT`; do sleep 3; done
echo "******* Discovery Service has started"

echo "********************************************************"
echo "Waiting for Configuration Service starting on port $CONFIGSERVER_PORT"
echo "********************************************************"
while ! `nc -z configurationservice $CONFIGSERVER_PORT `; do sleep 3; done
echo ">>>>>>>>>>>> Configuration Service has started"

#!/bin/sh
echo "********************************************************"
echo "Starting Service Gateway"
echo "********************************************************"
java -Deureka.client.serviceUrl.defaultZone=$DISCOVERYSERVICE_URI           \
     -Dspring.cloud.config.uri=$CONFIGSERVER_URI                            \
     -Dspring.profiles.active=$PROFILE  -jar /usr/local/servicegateway/@project.build.finalName@.jar

echo ">>>>>>>>>>>> Service Gateway started!"