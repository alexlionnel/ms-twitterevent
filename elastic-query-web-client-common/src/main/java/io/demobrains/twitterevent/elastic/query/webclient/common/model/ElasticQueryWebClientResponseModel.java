package io.demobrains.twitterevent.elastic.query.webclient.common.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ElasticQueryWebClientResponseModel {

    private String id;
    private Long userId;
    private String text;
    private ZonedDateTime createdAt;
}