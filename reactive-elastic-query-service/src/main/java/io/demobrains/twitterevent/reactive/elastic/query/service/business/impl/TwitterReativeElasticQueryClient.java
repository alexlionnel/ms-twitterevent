package io.demobrains.twitterevent.reactive.elastic.query.service.business.impl;

import io.demobrains.twitterevent.dataconfig.config.ElasticQueryServiceConfigData;
import io.demobrains.twitterevent.elasticmodel.impl.TwitterIndexModel;
import io.demobrains.twitterevent.reactive.elastic.query.service.business.ReactiveElasticQueryClient;
import io.demobrains.twitterevent.reactive.elastic.query.service.repository.ElasticQueryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Service
@Slf4j
@RequiredArgsConstructor
public class TwitterReativeElasticQueryClient implements ReactiveElasticQueryClient<TwitterIndexModel> {

    private final ElasticQueryRepository elasticQueryRepository;
    private final ElasticQueryServiceConfigData elasticQueryServiceConfigData;

    @Override
    public Flux<TwitterIndexModel> getIndexModelByText(String text) {
        log.info("Getting data from elasticsearch with text {}", text);
        return elasticQueryRepository
                .findByText(text)
                .delayElements(Duration.ofMillis(elasticQueryServiceConfigData.getBackPressureDelayMs()));
    }
}
