
FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app


COPY target/*.jar app.jar


RUN adduser -D myuser


USER myuser


EXPOSE 8080


ENTRYPOINT ["java", "-jar", "app.jar"]
