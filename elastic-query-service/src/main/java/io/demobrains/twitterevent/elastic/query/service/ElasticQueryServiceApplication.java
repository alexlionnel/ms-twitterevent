package io.demobrains.twitterevent.elastic.query.service;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import static org.springframework.boot.SpringApplication.run;

@SpringBootApplication(scanBasePackages = {"io.demobrains.twitterevent"})
@EnableEurekaClient
public class ElasticQueryServiceApplication {

    public static void main(String[] args) {
        run(ElasticQueryServiceApplication.class, args);
    }
}
