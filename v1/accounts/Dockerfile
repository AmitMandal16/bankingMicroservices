#Start with a base image conatining Java Runtime
FROM openjdk:17-jdk-slim

#MAINTAINER amit.mandal

#Application jar copy to the image
COPY target/accounts-0.0.1-SNAPSHOT.jar accounts-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","accounts-0.0.1-SNAPSHOT.jar"]
