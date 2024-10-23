# Dockerfile for Java Spring Boot application

# Use official OpenJDK image as base image
FROM openjdk:17-jdk-slim

# Set working directory inside the container
WORKDIR /app

# Copy the packaged JAR file into the container
COPY target/rep0531-0.0.1-SNAPSHOT.jar /app/rep0531-0.0.1-SNAPSHOT.jar

# Expose the port that your application runs on
EXPOSE 8888

# Command to run your application
CMD ["java", "-jar", "rep0531-0.0.1-SNAPSHOT.jar"]
