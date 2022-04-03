FROM adoptopenjdk/openjdk11
EXPOSE 9492

ENV TZ=Europe/Moscow

RUN apt-get update \
 && apt-get update && apt-get install -y curl

ENV APP_HOME=/usr/app
WORKDIR $APP_HOME
COPY reactive-elastic-query-service.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]
