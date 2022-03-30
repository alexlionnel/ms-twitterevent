package io.demobrains.twitterevent.elasticclient.repository;

import io.demobrains.twitterevent.elasticmodel.impl.TwitterIndexModel;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TwitterElasticsearchIndexRepository extends ElasticsearchRepository<TwitterIndexModel, String> {
}
