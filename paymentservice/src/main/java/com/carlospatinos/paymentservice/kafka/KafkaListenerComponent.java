package com.carlospatinos.paymentservice.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.carlospatinos.paymentservice.model.NotificationMessage;
import com.carlospatinos.stockservice.StockMessage;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class KafkaListenerComponent {
    @Value(value = "${application.topic}")
    private String appTopic;

    @Value(value = "${notificationservice.submission.url}")
    private String submissionUrl;

    @Autowired
    private RestTemplate restTemplate;

    @KafkaListener(topics = "${application.topic}", containerFactory = "kafkaListenerContainerFactory", groupId = "${spring.kafka.consumer.group-id}")
    public void listen(Message<StockMessage> transactionEventMessage) {
        log.info("Starting consuming from topic - {}", transactionEventMessage);
        StockMessage stockMesage = transactionEventMessage.getPayload();
        NotificationMessage message = new NotificationMessage(stockMesage.getUser(),
                "Congratulations",
                "New stock [" + stockMesage.getName() + "] in your portfolio.");

        ResponseEntity<String> result = restTemplate.postForEntity(submissionUrl,
                message, String.class);
        log.info("Result: {}", result);
    }
}