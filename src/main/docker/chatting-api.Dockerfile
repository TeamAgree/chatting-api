# Build stage
FROM eclipse-temurin:17-jdk AS builder

RUN apt-get update && apt-get install -y findutils

WORKDIR /workspace/chatting-api
COPY ./chatting-api .
RUN ./gradlew bootJar

# Run stage
FROM eclipse-temurin:17-jdk
COPY --from=builder /workspace/chatting-api/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
