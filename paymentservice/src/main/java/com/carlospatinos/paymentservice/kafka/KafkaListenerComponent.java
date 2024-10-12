package com.carlospatinos.paymentservice.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import com.carlospatinos.stockservice.StockMessage;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class KafkaListenerComponent {
    @Value(value = "${application.topic}")
    private String appTopic;

    @Autowired
    private ExternalApi apiCaller;

    @KafkaListener(topics = "${application.topic}", containerFactory = "kafkaListenerContainerFactory", groupId = "${spring.kafka.consumer.group-id}")
    public void listen(Message<StockMessage> transactionEventMessage) {
        log.info("Starting consuming from topic - {}", transactionEventMessage);
        StockMessage stockMessage = transactionEventMessage.getPayload();

        try {
            apiCaller.sendNotification(stockMessage);
        } catch (Exception e) {
            log.error("External API error.");
        }

    }
}