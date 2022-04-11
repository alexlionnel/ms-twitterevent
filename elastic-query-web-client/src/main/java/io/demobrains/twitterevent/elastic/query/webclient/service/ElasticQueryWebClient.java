package io.demobrains.twitterevent.elastic.query.webclient.service;

import io.demobrains.twitterevent.elastic.query.webclient.common.model.ElasticQueryWebClientAnalyticsResponseModel;
import io.demobrains.twitterevent.elastic.query.webclient.common.model.ElasticQueryWebClientRequestModel;

public interface ElasticQueryWebClient {

    ElasticQueryWebClientAnalyticsResponseModel getDataByText(ElasticQueryWebClientRequestModel requestModel);
}
