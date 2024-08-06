# Step 1: Use an appropriate base image with JDK 17
FROM openjdk:17-jdk-slim as build

WORKDIR /app

# Copy Gradle Wrapper files
COPY gradlew /app/
COPY gradle /app/gradle

# Copy Gradle files
COPY build.gradle settings.gradle /app/

# Copy the application source code
COPY src /app/src

# Make the Gradle Wrapper executable
RUN chmod +x ./gradlew

# Run Gradle build using the Wrapper
RUN ./gradlew build -x test --stacktrace

# Step 2: Use an appropriate base image for running the application
FROM openjdk:17-jdk-slim

WORKDIR /app

# Copy the built application from the build stage
COPY --from=build /app/build/libs/*.jar /app/app.jar

# Expose the port the app runs on
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "app.jar"]