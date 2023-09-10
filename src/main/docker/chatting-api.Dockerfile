# Build stage
FROM openjdk:17 AS builder
WORKDIR /workspace/app
COPY . .
RUN ./gradlew build

# Run stage
FROM openjdk:17
COPY --from=builder /workspace/app/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
