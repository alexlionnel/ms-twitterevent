package io.demobrains.twitterevent.analytics.service.business.impl;

import io.demobrains.twitterevent.analytics.service.business.AnalyticsService;
import io.demobrains.twitterevent.analytics.service.dataaccess.repository.AnalyticsRepository;
import io.demobrains.twitterevent.analytics.service.model.AnalyticsResponseModel;
import io.demobrains.twitterevent.analytics.service.transformer.EntityToResponseModelTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TwitterAnalyticsService implements AnalyticsService {

    private final AnalyticsRepository analyticsRepository;

    private final EntityToResponseModelTransformer entityToResponseModelTransformer;

    @Override
    public Optional<AnalyticsResponseModel> getWordAnalytics(String word) {
        return entityToResponseModelTransformer.getResponseModel(
                analyticsRepository.getAnalyticsEntitiesByWord(word, PageRequest.of(0, 1))
                        .stream().findFirst().orElse(null));
    }
}
