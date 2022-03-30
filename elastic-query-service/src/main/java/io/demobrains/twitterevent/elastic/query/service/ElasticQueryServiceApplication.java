package io.demobrains.twitterevent.elastic.query.service;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import static org.springframework.boot.SpringApplication.run;

@SpringBootApplication(scanBasePackages = {"io.demobrains.twitterevent"})
public class ElasticQueryServiceApplication {

    public static void main(String[] args) {
        run(ElasticQueryServiceApplication.class, args);
    }
}
