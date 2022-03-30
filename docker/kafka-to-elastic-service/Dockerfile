FROM adoptopenjdk/openjdk11
EXPOSE 8888

ENV TZ=Europe/Moscow

RUN apt-get update \
 && apt-get update && apt-get install -y curl

ENV APP_HOME=/usr/app
WORKDIR $APP_HOME
COPY kafka-to-elastic-service.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]
HEALTHCHECK --interval=10s --timeout=10s --retries=30 \
  CMD curl --silent --fail localhost:8888/actuator/health || exit 1
