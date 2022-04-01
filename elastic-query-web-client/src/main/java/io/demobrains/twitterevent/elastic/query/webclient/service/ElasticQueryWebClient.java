package io.demobrains.twitterevent.elastic.query.webclient.service;

import io.demobrains.twitterevent.elastic.query.webclient.model.ElasticQueryWebClientRequestModel;
import io.demobrains.twitterevent.elastic.query.webclient.model.ElasticQueryWebClientResponseModel;

import java.util.List;

public interface ElasticQueryWebClient {

    List<ElasticQueryWebClientResponseModel> getDataByText(ElasticQueryWebClientRequestModel requestModel);
}
