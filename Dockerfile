FROM gradle:7.4.2-jdk11 AS build

WORKDIR /app

COPY build.gradle settings.gradle ./
COPY gradle ./gradle

RUN gradle build -x test --stacktrace

COPY . .

RUN gradle build -x test --stacktrace

FROM openjdk:11-jre-slim

WORKDIR /app

COPY --from=build /app/build/libs/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]

EXPOSE 443