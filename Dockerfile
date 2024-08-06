# Step 1: Use an appropriate base image with JDK 11
FROM openjdk:11-jdk-slim as build

WORKDIR /app

# Install Gradle
RUN apt-get update && apt-get install -y wget unzip \
    && wget https://services.gradle.org/distributions/gradle-7.4.2-bin.zip -P /tmp \
    && unzip -d /opt/gradle /tmp/gradle-7.4.2-bin.zip \
    && ln -s /opt/gradle/gradle-7.4.2/bin/gradle /usr/bin/gradle

# Copy Gradle build files
COPY build.gradle settings.gradle /app/

# Copy source code
COPY src /app/src

# Build the application
RUN gradle build -x test --stacktrace

# Step 2: Create the actual image to run the application
FROM openjdk:11-jre-slim

WORKDIR /app

# Copy the built JAR file from the build stage
COPY --from=build /app/build/libs/*.jar /app/app.jar

# Expose the port the application runs on
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "app.jar"]