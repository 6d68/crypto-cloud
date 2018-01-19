#!/bin/sh

echo "********************************************************"
echo "Waiting for the Discovery Service to start on port $DISCOVERYSERVICE_PORT"
echo "********************************************************"
while ! `nc -z discovery-service  $DISCOVERYSERVICE_PORT`; do sleep 3; done
echo "******* Discovery Service has started"

echo "********************************************************"
echo "Starting Configuration Service"
echo "********************************************************"
java  -Deureka.client.serviceUrl.defaultZone=$DISCOVERYSERVICE_URI -jar /usr/local/configurationservice/@project.build.finalName@.jar

echo "********************************************************"
echo "Configuration Service started"
echo "********************************************************"
