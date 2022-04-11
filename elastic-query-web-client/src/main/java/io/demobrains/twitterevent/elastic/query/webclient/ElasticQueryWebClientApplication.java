package io.demobrains.twitterevent.elastic.query.webclient;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import static org.springframework.boot.SpringApplication.run;

@SpringBootApplication(scanBasePackages = {"io.demobrains.twitterevent"})
@EnableEurekaClient
public class ElasticQueryWebClientApplication {

    public static void main(String[] args) {
        run(ElasticQueryWebClientApplication.class, args);
    }
}
