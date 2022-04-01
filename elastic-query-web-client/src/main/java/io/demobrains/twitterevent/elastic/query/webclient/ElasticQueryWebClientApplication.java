package io.demobrains.twitterevent.elastic.query.webclient;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import static org.springframework.boot.SpringApplication.run;

@SpringBootApplication(scanBasePackages = {"io.demobrains.twitterevent"})
public class ElasticQueryWebClientApplication {

    public static void main(String[] args) {
        run(ElasticQueryWebClientApplication.class, args);
    }
}
