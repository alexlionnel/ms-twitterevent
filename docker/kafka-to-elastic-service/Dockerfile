FROM adoptopenjdk/openjdk11
EXPOSE 9491

ENV TZ=Europe/Moscow

RUN apt-get update \
 && apt-get update && apt-get install -y curl

ENV APP_HOME=/usr/app
WORKDIR $APP_HOME
COPY kafka-to-elastic-service.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]
