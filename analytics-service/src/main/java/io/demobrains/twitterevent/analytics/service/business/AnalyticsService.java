package io.demobrains.twitterevent.analytics.service.business;

import io.demobrains.twitterevent.analytics.service.model.AnalyticsResponseModel;

import java.util.Optional;

public interface AnalyticsService {

    Optional<AnalyticsResponseModel> getWordAnalytics(String word);
}
