package io.demobrains.twitterevent.reactive.elastic.query.web.client.config;

import io.demobrains.twitterevent.dataconfig.config.ElasticQueryWebClientConfigData;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.util.concurrent.TimeUnit;

@Configuration
public class WebClientConfig {

    private final ElasticQueryWebClientConfigData.WebClient webClientConfigData;

    public WebClientConfig(ElasticQueryWebClientConfigData webClientConfigData) {
        this.webClientConfigData = webClientConfigData.getWebClient();
    }

    @Bean("webClient")
    WebClient webClient() {
        return WebClient.builder()
                .baseUrl(webClientConfigData.getBaseUrl())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, webClientConfigData.getContentType())
                .clientConnector(new ReactorClientHttpConnector(getHttpClient()))
                .codecs(configurer -> configurer
                        .defaultCodecs()
                        .maxInMemorySize(webClientConfigData.getMaxInMemorySize()))
                .build();
    }

    private HttpClient getHttpClient() {
        return HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, webClientConfigData.getConnectTimeoutMs())
                .doOnConnected(connection -> {
                    connection.addHandlerLast(new ReadTimeoutHandler(webClientConfigData.getReadTimeoutMs(), TimeUnit.MILLISECONDS));
                    connection.addHandlerLast(new WriteTimeoutHandler(webClientConfigData.getWriteTimeoutMs(), TimeUnit.MILLISECONDS));
                });
    }
}

