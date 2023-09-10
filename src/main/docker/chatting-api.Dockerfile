# Build stage
FROM webhippie/temurin:17 AS builder

RUN apt-get update && apt-get install -y findutils

WORKDIR /workspace/chatting-api
COPY ./chatting-api .
RUN ./gradlew build

# Run stage
FROM webhippie/temurin:17
COPY --from=builder /workspace/chatting-api/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
