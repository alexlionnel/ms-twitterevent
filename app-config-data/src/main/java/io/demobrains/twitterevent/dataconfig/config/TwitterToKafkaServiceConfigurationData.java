package io.demobrains.twitterevent.dataconfig.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "twitter-to-kafka-service")
@Data
public class TwitterToKafkaServiceConfigurationData {

    private List<String> twitterKeywords;
    private Boolean enableMockTweets;
    private Integer mockMinTweetLength;
    private Integer mockMaxTweetLength;
    private Long mockSleepMs;
}
