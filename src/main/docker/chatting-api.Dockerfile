# Build stage
FROM openjdk:17 AS builder
WORKDIR /workspace/chatting-api
COPY . .
RUN ./chatting-api/gradlew build

# Run stage
FROM openjdk:17
COPY --from=builder /workspace/chatting-api/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
