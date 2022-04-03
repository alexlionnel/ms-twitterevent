package io.demobrains.twitterevent.reactive.elastic.query.service.business;

import io.demobrains.twitterevent.elastic.query.service.common.model.ElasticQueryServiceResponseModel;
import reactor.core.publisher.Flux;

public interface ElasticQueryService {

    Flux<ElasticQueryServiceResponseModel> getDocumentByText(String text);
}
