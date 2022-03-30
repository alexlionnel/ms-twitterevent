package io.demobrains.twitterevent.elastic.query.service.api;

import io.demobrains.twitterevent.elastic.query.service.model.ElasticQueryServiceRequestModel;
import io.demobrains.twitterevent.elastic.query.service.model.ElasticQueryServiceResponseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/documents")
public class ElasticDocumentController {

    private static final Logger LOG = LoggerFactory.getLogger(ElasticDocumentController.class);

    @GetMapping
    public ResponseEntity<List<ElasticQueryServiceResponseModel>> getAllDocuments() {
        List<ElasticQueryServiceResponseModel> response = new ArrayList<>();
        LOG.info("Elasticsearch returned {} of documents", response.size());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ElasticQueryServiceResponseModel> getDocumentById(@PathVariable String id) {
        ElasticQueryServiceResponseModel elasticQueryServiceResponseModel =
                ElasticQueryServiceResponseModel.builder().id(id).build();
        LOG.info("Elasticsearch returned document with id {}", id);
        return ResponseEntity.ok(elasticQueryServiceResponseModel);
    }

    @PostMapping("/text")
    public ResponseEntity<List<ElasticQueryServiceResponseModel>> getDocumentsByText(@RequestBody ElasticQueryServiceRequestModel elasticQueryServiceRequestModel) {
        List<ElasticQueryServiceResponseModel> response = new ArrayList<>();
        ElasticQueryServiceResponseModel elasticQueryServiceResponseModel =
                ElasticQueryServiceResponseModel.builder().text(elasticQueryServiceRequestModel.getText()).build();
        response.add(elasticQueryServiceResponseModel);
        LOG.info("Elasticsearch returned {} of documents", response.size());
        return ResponseEntity.ok(response);
    }
}
