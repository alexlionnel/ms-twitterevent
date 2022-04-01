package io.demobrains.twitterevent.elastic.query.service.model.assembler;

import io.demobrains.twitterevent.elastic.query.service.api.ElasticDocumentController;
import io.demobrains.twitterevent.elastic.query.service.model.ElasticQueryServiceResponseModel;
import io.demobrains.twitterevent.elastic.query.service.transformer.ElasticToResponseModelTransformer;
import io.demobrains.twitterevent.elasticmodel.impl.TwitterIndexModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ElasticQueryServiceResponseModelAssembler extends RepresentationModelAssemblerSupport<TwitterIndexModel, ElasticQueryServiceResponseModel> {

    private final ElasticToResponseModelTransformer elasticToResponseModelTransformer;

    public ElasticQueryServiceResponseModelAssembler(ElasticToResponseModelTransformer transformer) {
        super(ElasticDocumentController.class, ElasticQueryServiceResponseModel.class);
        this.elasticToResponseModelTransformer = transformer;
    }

    @Override
    public ElasticQueryServiceResponseModel toModel(TwitterIndexModel twitterIndexModel) {
        ElasticQueryServiceResponseModel responseModel = elasticToResponseModelTransformer.getResponseModel(twitterIndexModel);
        responseModel.add(
                linkTo(methodOn(ElasticDocumentController.class).getDocumentById(twitterIndexModel.getId()))
                        .withSelfRel()
        );
        responseModel.add(
                linkTo(ElasticDocumentController.class)
                        .withRel("documents")
        );
        return responseModel;
    }

    public List<ElasticQueryServiceResponseModel> toModels(List<TwitterIndexModel> twitterIndexModels) {
        return twitterIndexModels.stream().map(this::toModel).collect(Collectors.toList());
    }
}
