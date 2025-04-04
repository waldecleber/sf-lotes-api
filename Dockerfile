#### Stage 1: Build the application
FROM adoptopenjdk/openjdk11:ubi as build

# Set the current working directory inside the image
WORKDIR /app

COPY build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar", "app.jar"]