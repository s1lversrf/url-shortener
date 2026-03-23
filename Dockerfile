FROM gradle:8.14.0-jdk21 AS build

WORKDIR /app

COPY gradlew .
COPY gradle gradle

COPY settings.gradle .

COPY build.gradle .
COPY src ./src

RUN chmod +x gradlew

RUN SPRING_PROFILES_ACTIVE=build ./gradlew clean build -x test --no-daemon
#RUN ./gradlew clean build -x test --no-daemon

FROM eclipse-temurin:21-jdk-jammy

WORKDIR /app

COPY --from=build app/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]