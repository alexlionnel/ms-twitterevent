package io.demobrains.twitterevent.reactive.elastic.query.web.client;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import static org.springframework.boot.SpringApplication.run;

@SpringBootApplication(scanBasePackages = {"io.demobrains.twitterevent"})
public class ReactiveElasticQueryWebClientApplication {

    public static void main(String[] args) {
        run(ReactiveElasticQueryWebClientApplication.class, args);
    }
}
