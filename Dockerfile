FROM gradle:7.4.2-jdk11 as build

WORKDIR /app

COPY gradlew /app/
COPY gradle /app/gradle

COPY build.gradle settings.gradle /app/

COPY src /app/src

RUN chmod +x ./gradlew

RUN ./gradlew build -x test

FROM openjdk:11-jre-slim

WORKDIR /app

COPY --from=build /app/build/libs/*.jar /app/app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]