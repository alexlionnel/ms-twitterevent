package io.demobrains.twitterevent.reactive.elastic.query.web.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.stereotype.Component;

@Component
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity httpSecurity) {
        httpSecurity.authorizeExchange().anyExchange().permitAll();
        httpSecurity.csrf().disable();
        return httpSecurity.build();
    }
}
