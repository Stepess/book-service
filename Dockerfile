FROM openjdk:11.0.7-jre-slim
VOLUME /tmp
ARG JAR_FILE
COPY bin/run.sh rub.sh
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["run.sh"]
