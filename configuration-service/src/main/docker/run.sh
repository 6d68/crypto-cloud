#!/bin/sh
echo "********************************************************"
echo "Starting Configuration Service"
echo "********************************************************"
java -jar /usr/local/configurationservice/@project.build.finalName@.jar
echo "********************************************************"
echo "Configuration Service started"
echo "********************************************************"
