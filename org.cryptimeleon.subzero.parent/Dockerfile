# syntax=docker/dockerfile:1
FROM maven:3.8.1-jdk-11 as builder

WORKDIR /app
COPY . .
RUN mvn install

WORKDIR /app/org.cryptimeleon.subzero.web
RUN mvn compile war:war

FROM tomcat:jdk11-openjdk
EXPOSE 8080
COPY --from=0 /app/org.cryptimeleon.subzero.web/target/subzero.war /usr/local/tomcat/webapps/
