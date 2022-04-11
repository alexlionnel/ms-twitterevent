FROM adoptopenjdk/openjdk11

ENV TZ=Europe/Moscow

RUN apt-get update \
 && apt-get update && apt-get install -y curl

ENV APP_HOME=/usr/app
WORKDIR $APP_HOME
COPY discovery-service.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]
