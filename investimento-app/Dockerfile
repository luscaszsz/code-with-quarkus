FROM eclipse-temurin:21-jre

WORKDIR /app
COPY target/*-runner.jar app.jar

EXPOSE 8081

CMD ["java", "-jar", "app.jar"]