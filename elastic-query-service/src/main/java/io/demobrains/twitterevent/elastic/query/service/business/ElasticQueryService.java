package io.demobrains.twitterevent.elastic.query.service.business;

import io.demobrains.twitterevent.elastic.query.service.common.model.ElasticQueryServiceResponseModel;
import io.demobrains.twitterevent.elastic.query.service.model.ElasticQueryServiceAnalyticsResponseModel;

import java.util.List;

public interface ElasticQueryService {

    ElasticQueryServiceResponseModel getDocumentById(String id);
    ElasticQueryServiceAnalyticsResponseModel getDocumentByText(String text, String accessToken);
    List<ElasticQueryServiceResponseModel> getAllDocuments();
}
