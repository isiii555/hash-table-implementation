FROM openjdk:21-jdk-slim

WORKDIR /app

COPY /build/libs/hash-table-implementation-0.0.1-SNAPSHOT.jar /app/my-app.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "/app/my-app.jar"]
