package io.demobrains.twitterevent.elastic.query.webclient.service;

import io.demobrains.twitterevent.elastic.query.webclient.common.model.ElasticQueryWebClientRequestModel;
import io.demobrains.twitterevent.elastic.query.webclient.common.model.ElasticQueryWebClientResponseModel;

import java.util.List;

public interface ElasticQueryWebClient {

    List<ElasticQueryWebClientResponseModel> getDataByText(ElasticQueryWebClientRequestModel requestModel);
}
