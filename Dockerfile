FROM openjdk:17-jdk-alpine

MAINTAINER khater.com

COPY target/*.jar app.jar

ENTRYPOINT ["Java" , "-jar" , "/app.jar"]