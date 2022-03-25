# Getting Started

### Reference Documentation

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.6.4/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.6.4/maven-plugin/reference/html/#build-image)

#### Vérifier que les brockers sont bien démarrés
```
docker run --tty --network=host confluentinc/cp-kafkacat kafkacat -L -b kafka:19092
docker run --tty --network=host confluentinc/cp-kafkacat kafkacat -L -b kafka:29092
docker run --tty --network=host confluentinc/cp-kafkacat kafkacat -L -b kafka:39092
```

### Consulter les messages reçues par les brokers
```
docker run --tty --network=host confluentinc/cp-kafkacat kafkacat -C -b localhost:19092 -t twitter-topic
docker run --tty --network=host confluentinc/cp-kafkacat kafkacat -C -b localhost:29092 -t twitter-topic
docker run --tty --network=host confluentinc/cp-kafkacat kafkacat -C -b localhost:39092 -t twitter-topic
```