package io.demobrains.twitterevent.elasticclient.service;

import io.demobrains.twitterevent.elasticmodel.IndexModel;

import java.util.List;

public interface ElasticIndexClient <T extends IndexModel> {

    List<String> save(List<T> documents);
}
