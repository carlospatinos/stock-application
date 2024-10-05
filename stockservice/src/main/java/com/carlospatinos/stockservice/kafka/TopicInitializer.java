package com.carlospatinos.stockservice.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class TopicInitializer {
    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;

    @Value(value = "${application.topic}")
    private String appTopic;

    // @Bean
    // public KafkaAdmin kafkaAdmin() {
    // Map<String, Object> configs = new HashMap<>();
    // configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
    // return new KafkaAdmin(configs);
    // }

    @Bean
    public NewTopic topic() {
        log.info("Topic created: {}", appTopic);
        // return new NewTopic(appTopic, 1, (short) 1);
        return TopicBuilder.name(appTopic).build();
    }
}
