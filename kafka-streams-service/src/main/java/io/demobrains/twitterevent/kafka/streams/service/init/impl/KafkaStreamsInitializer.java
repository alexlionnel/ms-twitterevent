package io.demobrains.twitterevent.kafka.streams.service.init.impl;

import io.demobrains.twitterevent.dataconfig.config.KafkaConfigData;
import io.demobrains.twitterevent.kafka.streams.service.init.StreamsInitializer;
import io.demobrains.twitterevent.kafkaadmin.client.KafkaAdminClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class KafkaStreamsInitializer implements StreamsInitializer {

    private final KafkaConfigData kafkaConfigData;
    private final KafkaAdminClient kafkaAdminClient;

    @Override
    public void init() {
        kafkaAdminClient.checkTopicsCreated();
        kafkaAdminClient.checkSchemaRegistry();
        log.info("Topics with name {} is ready for operations!", kafkaConfigData.getTopicNamesToCreate().toArray());
    }
}
