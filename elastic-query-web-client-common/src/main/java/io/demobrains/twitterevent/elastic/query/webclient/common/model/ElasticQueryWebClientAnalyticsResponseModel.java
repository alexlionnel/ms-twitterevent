package io.demobrains.twitterevent.elastic.query.webclient.common.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ElasticQueryWebClientAnalyticsResponseModel {
    private List<ElasticQueryWebClientResponseModel> queryResponseModels;
    private Long wordCount;
}
