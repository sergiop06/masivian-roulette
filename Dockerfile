FROM openjdk:11-jdk
FROM maven:3-jdk-11
VOLUME /tmp
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]
EXPOSE 8080