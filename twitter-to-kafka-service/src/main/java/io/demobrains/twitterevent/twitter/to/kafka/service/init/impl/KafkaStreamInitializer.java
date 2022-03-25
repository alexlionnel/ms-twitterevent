package io.demobrains.twitterevent.twitter.to.kafka.service.init.impl;

import io.demobrains.twitterevent.dataconfig.config.KafkaConfigData;
import io.demobrains.twitterevent.kafkaadmin.client.KafkaAdminClient;
import io.demobrains.twitterevent.twitter.to.kafka.service.init.Streaminitializer;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaStreamInitializer implements Streaminitializer {

    private static final Logger LOG = LoggerFactory.getLogger(KafkaStreamInitializer.class);

    private final KafkaConfigData kafkaConfigData;
    private final KafkaAdminClient kafkaAdminClient;

    @Override
    public void init() {
        kafkaAdminClient.createTopics();
        kafkaAdminClient.checkSchemaRegistry();
        LOG.info("Topic with name {} is ready for operations!", kafkaConfigData.getTopicNamesToCreate().toArray());
    }
}
