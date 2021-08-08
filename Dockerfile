# syntax=docker/dockerfile:1

FROM node:lts-alpine as builder1
WORKDIR /app
COPY ["org.cryptimeleon.subzero.parent/", "*.md", "website/package*.json", "website/*.config.js", "website/*.html", "website/*.css", "website/*.sh", "./"]
RUN npm install
COPY website/src ./src/
RUN ./preprocessing.sh
RUN npm run build

FROM maven:3.8.1-jdk-11 as builder2
WORKDIR /app
COPY --from=builder1 /app/ .
RUN mvn install

WORKDIR /app/org.cryptimeleon.subzero.web
RUN mvn compile war:war

FROM tomcat:jdk11-openjdk
EXPOSE 8080
COPY --from=builder2 /app/org.cryptimeleon.subzero.web/target/subzero.war /usr/local/tomcat/webapps/
