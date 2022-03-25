package io.demobrains.twitterevent.kafkaadmin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean(name = "kafka_admin")
    WebClient webClient() {
        return WebClient.builder().build();
    }
}
