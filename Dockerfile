FROM gradle:7.6-jdk17 AS builder
WORKDIR /app
COPY . .
RUN chmod +x gradlew
RUN ./gradlew clean build -x test

FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar app.jar
