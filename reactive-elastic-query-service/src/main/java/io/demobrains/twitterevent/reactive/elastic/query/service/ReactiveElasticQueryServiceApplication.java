package io.demobrains.twitterevent.reactive.elastic.query.service;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import static org.springframework.boot.SpringApplication.run;

@SpringBootApplication(scanBasePackages = {"io.demobrains.twitterevent"})
public class ReactiveElasticQueryServiceApplication {

    public static void main(String[] args) {
        run(ReactiveElasticQueryServiceApplication.class, args);
    }
}
