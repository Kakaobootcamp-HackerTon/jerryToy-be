# Step 1: Use an appropriate base image with JDK 11
FROM openjdk:11-jdk-slim as build

WORKDIR /app

RUN echo "systemProp.http.proxyHost=krmp-proxy.9rum.cc\nsystemProp.http.proxyPort=3128\nsystemProp.https.proxyHost=krmp-proxy.9rum.cc\nsystemProp.https.proxyPort=3128" > /root/.gradle/gradle.properties

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