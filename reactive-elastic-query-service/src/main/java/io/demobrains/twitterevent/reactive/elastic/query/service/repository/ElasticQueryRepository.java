package io.demobrains.twitterevent.reactive.elastic.query.service.repository;

import io.demobrains.twitterevent.elasticmodel.impl.TwitterIndexModel;
import org.springframework.data.elasticsearch.repository.ReactiveElasticsearchRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ElasticQueryRepository extends ReactiveElasticsearchRepository<TwitterIndexModel, String> {

    Flux<TwitterIndexModel> findByText(String text);
}
