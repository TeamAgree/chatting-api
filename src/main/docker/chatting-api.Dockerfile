# Build stage
FROM adoptopenjdk:17-jdk-hotspot AS builder

RUN apt-get update && apt-get install -y findutils

WORKDIR /workspace/chatting-api
COPY ./chatting-api .
RUN ./gradlew build

# Run stage
FROM adoptopenjdk:17-jdk-hotspot
COPY --from=builder /workspace/chatting-api/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
