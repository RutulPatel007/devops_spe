# Use Java 17 base image
FROM openjdk:25-ea-slim

# Set working directory
WORKDIR /app

# Copy jar from Maven target
COPY target/devops_spe-1.0-SNAPSHOT.jar calculator.jar

# Run the application
ENTRYPOINT ["java", "-jar", "calculator.jar"]