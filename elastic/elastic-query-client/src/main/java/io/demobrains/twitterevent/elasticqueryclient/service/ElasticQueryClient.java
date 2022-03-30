package io.demobrains.twitterevent.elasticqueryclient.service;

import io.demobrains.twitterevent.elasticmodel.IndexModel;

import java.util.List;

public interface ElasticQueryClient<T extends IndexModel> {

    T getIndexModelById(String id);
    List<T> getIndexModelByText(String text);
    List<T> getAllIndexModels();
}


