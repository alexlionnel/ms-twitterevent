package io.demobrains.twitterevent.elastic.query.service.business.impl;

import io.demobrains.twitterevent.elastic.query.service.business.ElasticQueryService;
import io.demobrains.twitterevent.elastic.query.service.model.ElasticQueryServiceResponseModel;
import io.demobrains.twitterevent.elastic.query.service.model.assembler.ElasticQueryServiceResponseModelAssembler;
import io.demobrains.twitterevent.elastic.query.service.transformer.ElasticToResponseModelTransformer;
import io.demobrains.twitterevent.elasticmodel.impl.TwitterIndexModel;
import io.demobrains.twitterevent.elasticqueryclient.service.ElasticQueryClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TwitterElasticQueryService implements ElasticQueryService {

    private final ElasticQueryServiceResponseModelAssembler elasticQueryServiceResponseModelAssembler;
    private final ElasticQueryClient<TwitterIndexModel> elasticQueryClient;

    @Override
    public ElasticQueryServiceResponseModel getDocumentById(String id) {
        log.info("Querying elasticsearch by id {}", id);
        return elasticQueryServiceResponseModelAssembler.toModel(elasticQueryClient.getIndexModelById(id));
    }

    @Override
    public List<ElasticQueryServiceResponseModel> getDocumentByText(String text) {
        log.info("Querying elasticsearch by text {}", text);
        return elasticQueryServiceResponseModelAssembler.toModels(elasticQueryClient.getIndexModelByText(text));
    }

    @Override
    public List<ElasticQueryServiceResponseModel> getAllDocuments() {
        log.info("Querying all documents in elasticsearch");
        return elasticQueryServiceResponseModelAssembler.toModels(elasticQueryClient.getAllIndexModels());
    }
}
