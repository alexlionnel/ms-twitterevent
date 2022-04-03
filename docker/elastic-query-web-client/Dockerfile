FROM adoptopenjdk/openjdk11
EXPOSE 9493

ENV TZ=Europe/Moscow

RUN apt-get update \
 && apt-get update && apt-get install -y curl

ENV APP_HOME=/usr/app
WORKDIR $APP_HOME
COPY elastic-query-web-client.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]
