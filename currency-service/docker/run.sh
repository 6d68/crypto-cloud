#!/bin/sh
getPort() {
    echo $1 | cut -d : -f 3 | xargs basename
}

echo "********************************************************"
echo "Waiting for the Discovery Service to start on port $(getPort $DISCOVERYSERVICE_PORT)"
echo "********************************************************"
while ! `nc -z discovery-service  $(getPort $DISCOVERYSERVICE_PORT)`; do sleep 3; done
echo "******* Discovery Service has started"

echo "********************************************************"
echo "Waiting for Configuration Service starting on port $(getPort $CONFIGSERVER_PORT)"
echo "********************************************************"
while ! `nc -z configurationservice $(getPort $CONFIGSERVER_PORT)`; do sleep 3; done
echo ">>>>>>>>>>>> Configuration Service has started"

echo "********************************************************"
echo "waiting for Database Server starting on port $(getPort $DATABASESERVER_PORT)"
echo "********************************************************"
while ! `nc -z currencyservice-db $(getPort $DATABASESERVER_PORT)`; do sleep 3; done
echo ">>>>>>>>>>>> Database Server has started"

echo "********************************************************"
echo "Starting Currency Service";

java -Djava.security.egd=file:/dev/./urandom                             \
     -Deureka.client.serviceUrl.defaultZone=$DISCOVERYSERVICE_URI        \
     -Dspring.cloud.config.uri=$CONFIGSERVER_URI                         \
     -Dspring.profiles.active=$PROFILE                                   \
     -jar /usr/local/currencyservice/@project.build.finalName@.jar
echo ">>>>>>>>>>>> Currency Service started!"

