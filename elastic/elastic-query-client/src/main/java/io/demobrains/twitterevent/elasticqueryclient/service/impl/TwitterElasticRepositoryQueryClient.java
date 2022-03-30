package io.demobrains.twitterevent.elasticqueryclient.service.impl;

import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import io.demobrains.twitterevent.elasticmodel.impl.TwitterIndexModel;
import io.demobrains.twitterevent.elasticqueryclient.exception.ElasticQueryClientException;
import io.demobrains.twitterevent.elasticqueryclient.repository.TwitterElasticsearchQueryRepository;
import io.demobrains.twitterevent.elasticqueryclient.service.ElasticQueryClient;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Primary
@Service
@RequiredArgsConstructor
public class TwitterElasticRepositoryQueryClient implements ElasticQueryClient<TwitterIndexModel> {

    private static final Logger LOG = LoggerFactory.getLogger(TwitterElasticRepositoryQueryClient.class);

    private final TwitterElasticsearchQueryRepository twitterElasticsearchQueryRepository;

    @Override
    public TwitterIndexModel getIndexModelById(String id) {
        Optional<TwitterIndexModel> searchResult = twitterElasticsearchQueryRepository.findById(id);
        LOG.info("Document with id {} retrieved successfully",
                searchResult.orElseThrow(() -> new ElasticQueryClientException("No document found at elasticsearch with id " + id)).getId());
        return searchResult.get();
    }

    @Override
    public List<TwitterIndexModel> getIndexModelByText(String text) {
        List<TwitterIndexModel> searchResult = twitterElasticsearchQueryRepository.findByText(text);
        LOG.info("{} of documents with text {} retrieved successfully", searchResult.size(), text);
        return searchResult;
    }

    @Override
    public List<TwitterIndexModel> getAllIndexModels() {
        Iterable<TwitterIndexModel> searchResult = twitterElasticsearchQueryRepository.findAll();
        ArrayList<TwitterIndexModel> results = Lists.newArrayList(searchResult);
        LOG.info("{} number of documents retrieved successfully", results.size());
        return results;
    }
}
