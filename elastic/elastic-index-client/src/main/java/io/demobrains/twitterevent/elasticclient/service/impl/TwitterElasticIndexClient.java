package io.demobrains.twitterevent.elasticclient.service.impl;

import io.demobrains.twitterevent.dataconfig.config.ElasticConfigData;
import io.demobrains.twitterevent.elasticclient.service.ElasticIndexClient;
import io.demobrains.twitterevent.elasticclient.util.ElasticIndexUtil;
import io.demobrains.twitterevent.elasticmodel.impl.TwitterIndexModel;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.IndexedObjectInformation;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@ConditionalOnProperty(name = "elastic-config.is-repository", havingValue = "false")
@RequiredArgsConstructor
public class TwitterElasticIndexClient implements ElasticIndexClient<TwitterIndexModel> {

    private static final Logger LOG = LoggerFactory.getLogger(TwitterElasticIndexClient.class);

    private final ElasticConfigData elasticConfigData;
    private final ElasticsearchOperations elasticsearchOperations;
    private final ElasticIndexUtil<TwitterIndexModel> elasticIndexUtil;

    @Override
    public List<String> save(List<TwitterIndexModel> documents) {
        List<IndexQuery> indexQueries = elasticIndexUtil.getIndexQueries(documents);
        List<String> documentsIds = elasticsearchOperations.bulkIndex(indexQueries, IndexCoordinates.of(elasticConfigData.getIndexName()))
                .stream().map(IndexedObjectInformation::getId).collect(Collectors.toList());;
        LOG.info("Documents indexed successfully with type: {} and ids: {}", TwitterIndexModel.class.getName(), documentsIds);
        return documentsIds;
    }
}
