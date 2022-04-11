package io.demobrains.twitterevent.analytics.service.business.impl;

import io.demobrains.twitterevent.analytics.service.dataaccess.entity.AnalyticsEntity;
import io.demobrains.twitterevent.analytics.service.dataaccess.repository.AnalyticsRepository;
import io.demobrains.twitterevent.analytics.service.transformer.AvroToDbEntityModelTransformer;
import io.demobrains.twitterevent.dataconfig.config.KafkaConfigData;
import io.demobrains.twitterevent.kafkaadmin.client.KafkaAdminClient;
import io.demobrains.twitterevent.kafkaconsumer.api.KafkaConsumer;
import io.deobrains.twitterevent.kafkamodel.avro.model.TwitterAnalyticsAvroModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AnalyticsKafkaConsumer implements KafkaConsumer<TwitterAnalyticsAvroModel> {

    private final KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry;

    private final KafkaAdminClient kafkaAdminClient;

    private final KafkaConfigData kafkaConfig;

    private final AvroToDbEntityModelTransformer avroToDbEntityModelTransformer;

    private final AnalyticsRepository analyticsRepository;

    @EventListener
    public void onAppStarted(ApplicationStartedEvent event) {
        kafkaAdminClient.checkTopicsCreated();
        log.info("Topics with name {} is ready for operations!", kafkaConfig.getTopicNamesToCreate().toArray());
        kafkaListenerEndpointRegistry.getListenerContainer("twitterAnalyticsTopicListener").start();
    }

    @Override
    @KafkaListener(id = "twitterAnalyticsTopicListener", topics = "${kafka-config.topic-name}", autoStartup = "false")
    public void receive(@Payload List<TwitterAnalyticsAvroModel> messages,
                        @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) List<String> keys,
                        @Header(KafkaHeaders.RECEIVED_PARTITION_ID) List<Integer> partitions,
                        @Header(KafkaHeaders.OFFSET) List<Long> offsets) {
        log.info("{} number of messages received, sending it to database", messages.size());
        List<AnalyticsEntity> twitterAnalyticsEntities = avroToDbEntityModelTransformer.getEntityModel(messages);
        analyticsRepository.batchPersist(twitterAnalyticsEntities);
        log.info("{} number of messaged send to database", twitterAnalyticsEntities.size());

    }

}
