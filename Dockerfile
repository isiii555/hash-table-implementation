FROM openjdk:21
WORKDIR /app
COPY /build/libs/CustomHashTable-1.0-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar","app.jar"]