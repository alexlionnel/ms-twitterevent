package io.demobrains.twitterevent.gateway.controller;

import io.demobrains.twitterevent.gateway.model.AnalyticsDataFallbackModel;
import io.demobrains.twitterevent.gateway.model.QueryServiceFallbackModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fallback")
@Slf4j
public class FallbackController {

    @PostMapping("/query-fallback")
    public ResponseEntity<QueryServiceFallbackModel> queryServiceFallback() {
        log.info("Returning fallback result for elastic-query-service!");
        return ResponseEntity.ok(QueryServiceFallbackModel.builder()
                .fallbackMessage("Fallback result for elastic-query-service!")
                .build());
    }

    @PostMapping("/analytics-fallback")
    public ResponseEntity<AnalyticsDataFallbackModel> analyticsServiceFallback() {
        log.info("Returning fallback result for analytics-service!");
        return ResponseEntity.ok(AnalyticsDataFallbackModel.builder()
                .wordCount(0L)
                .build());
    }

    @PostMapping("/streams-fallback")
    public ResponseEntity<AnalyticsDataFallbackModel> streamsServiceFallback() {
        log.info("Returning fallback result for kafka-streams-service!");
        return ResponseEntity.ok(AnalyticsDataFallbackModel.builder()
                .wordCount(0L)
                .build());
    }


}
