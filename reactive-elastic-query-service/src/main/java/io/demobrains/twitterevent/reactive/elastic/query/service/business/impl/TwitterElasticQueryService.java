package io.demobrains.twitterevent.reactive.elastic.query.service.business.impl;

import io.demobrains.twitterevent.elastic.query.service.common.model.ElasticQueryServiceResponseModel;
import io.demobrains.twitterevent.elastic.query.service.common.transformer.ElasticToResponseModelTransformer;
import io.demobrains.twitterevent.elasticmodel.impl.TwitterIndexModel;
import io.demobrains.twitterevent.reactive.elastic.query.service.business.ElasticQueryService;
import io.demobrains.twitterevent.reactive.elastic.query.service.business.ReactiveElasticQueryClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Service
@Slf4j
@RequiredArgsConstructor
public class TwitterElasticQueryService implements ElasticQueryService {

    private final ReactiveElasticQueryClient<TwitterIndexModel> reactiveElasticQueryClient;
    private final ElasticToResponseModelTransformer elasticToResponseModelTransformer;

    @Override
    public Flux<ElasticQueryServiceResponseModel> getDocumentByText(String text) {
        log.info("Querying reactive elasticsearch by text {}", text);
        return reactiveElasticQueryClient
                .getIndexModelByText(text)
                .doOnNext(e -> log.info("Getting element {}", e.toString()))
                .map(elasticToResponseModelTransformer::getResponseModel);
    }

    @Override
    public Flux<ElasticQueryServiceResponseModel> getAllDocuments() {
        return reactiveElasticQueryClient
                .getAllIndexModel()
                .map(elasticToResponseModelTransformer::getResponseModel);
    }
}
