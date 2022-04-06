package io.demobrains.twitterevent.reactive.elastic.query.service.config;

import io.demobrains.twitterevent.dataconfig.config.ElasticConfigData;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.reactive.ReactiveElasticsearchClient;
import org.springframework.data.elasticsearch.client.reactive.ReactiveRestClients;
import org.springframework.data.elasticsearch.config.AbstractReactiveElasticsearchConfiguration;
import org.springframework.data.elasticsearch.core.ReactiveElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ReactiveElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableReactiveElasticsearchRepositories;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.InetSocketAddress;
import java.util.Objects;

@Configuration
@RequiredArgsConstructor
@EnableReactiveElasticsearchRepositories(basePackages = "io.demobrains.twitterevent")
public class ElasticsearchConfig extends AbstractReactiveElasticsearchConfiguration {

    private final ElasticConfigData elasticConfigData;

    @Override
    @Bean
    public ReactiveElasticsearchClient reactiveElasticsearchClient() {
        UriComponents serverUri = UriComponentsBuilder.fromHttpUrl(elasticConfigData.getConnectionUrl()).build();
        final ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo(InetSocketAddress.createUnresolved(Objects.requireNonNull(serverUri.getHost()), serverUri.getPort()))
                .withConnectTimeout(elasticConfigData.getConnectionTimeout())
                .withSocketTimeout(elasticConfigData.getSocketTimeout())
                .withClientConfigurer(
                        ReactiveRestClients.WebClientConfigurationCallback.from(webClient -> {
                            ExchangeStrategies exchangeStrategies = ExchangeStrategies.builder()
                                    .codecs(configurer -> configurer.defaultCodecs()
                                            .maxInMemorySize(-1))
                                    .build();
                            return webClient.mutate().exchangeStrategies(exchangeStrategies).build();
                        }))
                .build();
        return ReactiveRestClients.create(clientConfiguration);
    }

    @Bean
    public ReactiveElasticsearchOperations elasticsearchOperations(ReactiveElasticsearchClient reactiveElasticsearchClient) {
        return new ReactiveElasticsearchTemplate(reactiveElasticsearchClient);
    }
}
