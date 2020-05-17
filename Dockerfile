FROM openjdk:11.0.7-jre-slim
VOLUME /tmp

COPY build/libs/book-service-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
