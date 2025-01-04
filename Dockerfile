# Use an official OpenJDK image as the base
FROM eclipse-temurin:21-jdk-jammy


# Set the working directory inside the container
WORKDIR /app

# Copy the built JAR file into the container
COPY target/AdminService-0.0.1-SNAPSHOT.jar /app/AdminService-0.0.1-SNAPSHOT.jar


# Expose the port your application runs on (adjust if necessary)
EXPOSE 8081

# Command to run the application
ENTRYPOINT ["java", "-jar", "/app/AdminService-0.0.1-SNAPSHOT.jar"]

