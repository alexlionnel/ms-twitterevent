package io.demobrains.twitterevent.elastic.query.webclient.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ElasticQueryWebClientRequestModel {

    private String id;
    @NotEmpty
    private String text;
}
