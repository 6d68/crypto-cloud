#!/bin/sh
echo "********************************************************"
echo "Starting Discovery Service"
echo "********************************************************"
java -Djava.security.egd=file:/dev/./urandom -jar /usr/local/discoveryservice/@project.build.finalName@.jar
echo "********************************************************"
echo "Discovery Service has started"
echo "********************************************************"