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

echo "********************************************************"
echo "waiting for Database Server starting on port $DATABASESERVER_PORT"
echo "********************************************************"
while ! `nc -z currencyservice-db $DATABASESERVER_PORT`; do sleep 3; done
echo ">>>>>>>>>>>> Database Server has started"

echo "********************************************************"
echo "Starting Currency Service";

java -Djava.security.egd=file:/dev/./urandom                             \
     -Deureka.client.serviceUrl.defaultZone=$DISCOVERYSERVICE_URI        \
     -Dspring.cloud.config.uri=$CONFIGSERVER_URI                         \
     -Dspring.profiles.active=$PROFILE                                   \
     -jar /usr/local/currencyservice/@project.build.finalName@.jar
echo ">>>>>>>>>>>> Currency Service started!"

