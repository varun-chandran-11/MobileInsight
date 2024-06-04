FROM ubuntu:latest

LABEL maintainer="varun.chandran.11@gmail.com"
LABEL authors="varunc"

# Update the package list and install OpenJDK 17
RUN apt-get update && \
    apt-get install -y openjdk-17-jdk && \
    apt-get clean;

# Set the working directory in the container
WORKDIR /app

# Copy the application JAR file to the container
COPY target/mobile-insight-service-0.0.1.jar /app/mobile-insight-service.jar

# Expose the application port
EXPOSE 8080

# Specify the command to run the application
ENTRYPOINT ["java", "-jar", "/app/mobile-insight-service.jar"]