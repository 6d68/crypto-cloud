#!/bin/sh

echo "building client app ..."
cd crypto-cloud-app
npm install
npm run build
docker build -t 6d68/webapp .
cd ..
echo "building spring microservices ..."
mvn clean package docker:build