package io.demobrains.twitterevent.reactive.elastic.query.web.client.service.impl;

import io.demobrains.twitterevent.dataconfig.config.ElasticQueryWebClientConfigData;
import io.demobrains.twitterevent.elastic.query.webclient.common.exception.ElasticQueryWebClientException;
import io.demobrains.twitterevent.elastic.query.webclient.common.model.ElasticQueryWebClientRequestModel;
import io.demobrains.twitterevent.elastic.query.webclient.common.model.ElasticQueryWebClientResponseModel;
import io.demobrains.twitterevent.reactive.elastic.query.web.client.service.ElasticQueryWebClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class TwitterElasticQueryWebClient implements ElasticQueryWebClient {

    private final WebClient webClient;
    private final ElasticQueryWebClientConfigData elasticQueryWebClientConfigData;

    public TwitterElasticQueryWebClient(@Qualifier("webClient") WebClient webClient, ElasticQueryWebClientConfigData elasticQueryWebClientConfigData) {
        this.webClient = webClient;
        this.elasticQueryWebClientConfigData = elasticQueryWebClientConfigData;
    }

    @Override
    public Flux<ElasticQueryWebClientResponseModel> getDataByText(ElasticQueryWebClientRequestModel requestModel) {
        log.info("Querying by text {}", requestModel.getText());
        return getWebClient(requestModel).bodyToFlux(ElasticQueryWebClientResponseModel.class);
    }

    private WebClient.ResponseSpec getWebClient(ElasticQueryWebClientRequestModel requestModel) {
        return webClient
                .method(HttpMethod.valueOf(elasticQueryWebClientConfigData.getQueryByText().getMethod()))
                .uri(elasticQueryWebClientConfigData.getQueryByText().getUri())
                .accept(MediaType.valueOf(elasticQueryWebClientConfigData.getQueryByText().getAccept()))
                .body(BodyInserters.fromPublisher(Mono.just(requestModel), createParameterizedTypeReference()))
                .retrieve()
                .onStatus(
                        httpStatus -> httpStatus.equals(HttpStatus.UNAUTHORIZED),
                        clientResponse -> Mono.just(new BadCredentialsException("Not authenticated!")))
                .onStatus(
                        HttpStatus::is4xxClientError,
                        cr -> Mono.just(new ElasticQueryWebClientException(cr.statusCode().getReasonPhrase())))
                .onStatus(
                        HttpStatus::is5xxServerError,
                        cr -> Mono.just(new Exception(cr.statusCode().getReasonPhrase())));
    }


    private <T> ParameterizedTypeReference<T> createParameterizedTypeReference() {
        return new ParameterizedTypeReference<>() {
        };
    }
}
