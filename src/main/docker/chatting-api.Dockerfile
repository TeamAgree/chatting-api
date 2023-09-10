# Build stage
FROM openjdk:17-jdk-buster AS builder

RUN apt-get update && apt-get install -y findutils

WORKDIR /workspace/chatting-api
COPY . .
RUN ./chatting-api/gradlew build

# Run stage
FROM openjdk:17-jdk-buster
COPY --from=builder /chatting-api/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
