package io.demobrains.twitterevent.reactive.elastic.query.service.business;

import io.demobrains.twitterevent.elasticmodel.IndexModel;
import reactor.core.publisher.Flux;

public interface ReactiveElasticQueryClient<T extends IndexModel> {

    Flux<T> getIndexModelByText(String text);
}
