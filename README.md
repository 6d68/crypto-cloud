# Coinwatcher cloud
Personal project putting together all the stuff i learned about Microservices in the last couple of months. Project will cover service configuration, discovery, resiliency, routing, eventing, tacing and finally deployment. As i'm personal interested in crypto currencies like Ethereum et al i decided to build a "crypto cloud" with a couple of services around the topic. The following figure gives you a rough overview:

<img src="./cryptocloud.png">

Services are build on top Spring Boot and Spring Cloud and Netflix OSS either using Java or Kotlin. 

### Services
#### Currency Service

|Action|Method|Url|Body|
|---|---|---|---|
|List all currencies|GET |http://localhost:8000/currencies             ||
|Get currency by id |GET |http://localhost:8000/currencies/$currencyId$||
|Save currency      |POST|http://localhost:8000/currencies/            |<code>{<br>&nbsp;&nbsp;"id": "tether",<br>&nbsp;&nbsp;"name": "Tether",<br>&nbsp;&nbsp;"symbol": "USDT",<br>&nbsp;&nbsp;"lastUpdated": "1502012649",<br>&nbsp;&nbsp;"change1hInPercent": "-0.21",<br>&nbsp;&nbsp;"priceInPriceCurrency": "0.998024",<br>&nbsp;&nbsp;"priceCurrency": "USD",<br>&nbsp;&nbsp;"change7dInPercent": "-0.11",<br>&nbsp;&nbsp;"change24hInPercent": "-0.21"<br>}</code>|

#### Configuration Service
Service to manage Crypto-Cloud wide configuration for each service. At the moment only used by the currency service to configure the mongo database. To see the configuration start the cloud and navigate to the [Currency Service Default Configuration](http://localhost:8888/currencyservice/default). The technology behind this configuration service is [Spring Cloud Config](https://cloud.spring.io/spring-cloud-config/)

# Running the cloud on your local machine

## Pre requisites
1.	[Apache Maven](http://maven.apache.org).
2.	[Docker](http://docker.com).

## Building & Running the cloud on your local machine
First you have to build the code as a docker image. Therefor open a terminal, navigate to source code root directory and run the following maven command:

   **mvn clean package docker:build**

Once the build is finished you can start the cloud with the following docker-compose command:

   **docker-compose -f docker/docker-compose.yml up**

# Learning microservices
Finally a big thank you to the authors of "Building Microservices" (Sam Newman), "The TAO of Microservices" (Richard Rodger) and "Spring Microservices in Action" (John Carnell) who helped me to dive deeper in this complex topic.
