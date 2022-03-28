FROM adoptopenjdk/openjdk11
EXPOSE 9490

ENV TZ=Europe/Moscow

RUN apt-get update \
 && apt-get update && apt-get install -y curl

ENV APP_HOME=/usr/app
WORKDIR $APP_HOME
COPY twitter-to-kafka-service.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]
