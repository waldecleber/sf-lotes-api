#### Stage 1: Build the application
FROM openjdk:17-jdk-slim as build

# Set the current working directory inside the image
WORKDIR /app

COPY build/libs/sf-loteamentos-0.0.1-SNAPSHOT.jar /app/app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar", "/app/app.jar"]