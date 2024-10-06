package com.carlospatinos.paymentservice.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.carlospatinos.paymentservice.model.NotificationMessage;

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

    // TODO remove this hardcoded topic
    @KafkaListener(topics = "stocktopic", groupId = "group1")
    void listener(String data) {
        log.info("Received message [{}] in group1", data);

        NotificationMessage message = new NotificationMessage("carlos", "Congratulations",
                "New stock in your portfolio.");

        // restTemplate = new RestTemplate();
        ResponseEntity<String> result = restTemplate.postForEntity(submissionUrl,
                message, String.class);
        log.info("Result: {}", result);

    }
}