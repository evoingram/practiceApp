# Use an official OpenJDK runtime as a parent image
FROM openjdk:11-jre-slim

# Set the working directory in the container
WORKDIR /app

# Copy the build artifact from the target directory
COPY practiceapp/build/libs/practiceapp-0.0.1-SNAPSHOT.jar /app/practiceapp.jar

# Expose the port the app runs on
EXPOSE 8080

# Set the command to run the Spring Boot application
ENTRYPOINT ["java","-jar","practiceapp.jar"]
