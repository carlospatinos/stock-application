package com.carlospatinos.paymentservice.kafka;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class KafkaListenerComponent {
    @Value(value = "${application.topic}")
    private String appTopic;

    @KafkaListener(topics = "stocktopic", groupId = "group1")
    void listener(String data) {
        log.info("Received message [{}] in group1", data);
    }
}