FROM openjdk:8-jdk-alpine
RUN  apk update && apk upgrade && apk add netcat-openbsd
RUN mkdir -p /usr/local/apigateway
ADD @project.build.finalName@.jar /usr/local/apigateway/
ADD run.sh run.sh
RUN chmod +x run.sh
CMD ./run.sh
