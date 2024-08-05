# 빌드 단계
FROM gradle:7.4.2-jdk11 AS build

WORKDIR /app

# Gradle 캐시를 활용하기 위해 Gradle 사용자 홈 디렉토리 설정
ENV GRADLE_USER_HOME /cache

# 의존성 파일만 복사하여 의존성 캐싱
COPY build.gradle settings.gradle ./
RUN gradle build -x test --stacktrace || return 0

# 소스 코드 전체를 복사
COPY . .

# 애플리케이션 빌드
RUN gradle build -x test --stacktrace

# 실행 단계
FROM openjdk:11-jre-slim

WORKDIR /app

# 빌드 단계에서 생성된 JAR 파일을 복사
COPY --from=build /app/build/libs/*.jar app.jar

# 애플리케이션 실행
ENTRYPOINT ["java", "-jar", "app.jar"]

# 컨테이너가 실행할 포트 노출
EXPOSE 443