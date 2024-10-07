package com.carlospatinos.stockservice.kafka;

import java.util.concurrent.CompletableFuture;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import com.carlospatinos.stockservice.StockMessage;
import com.carlospatinos.stockservice.model.UserTransaction;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class KafkaSender {

    private KafkaTemplate<String, StockMessage> kafkaTemplate;

    @Autowired
    public KafkaSender(KafkaTemplate<String, StockMessage> template) {
        this.kafkaTemplate = template;
    };

    public void sendMessage(UserTransaction transactionEvent, String topicName) {
        StockMessage record = new StockMessage();
        record.setAmount(transactionEvent.getAmount());
        record.setName(transactionEvent.getStockName());
        record.setUser(transactionEvent.getUserName());
        record.setPrice(transactionEvent.getPrice());
        record.setAvailableUnits(0);

        ProducerRecord<String, StockMessage> producerRecord = new ProducerRecord<>(
                topicName, record);
        CompletableFuture<SendResult<String, StockMessage>> completableFuture = kafkaTemplate.send(producerRecord);
        log.info("Sending kafka message on topic {}", topicName);

        completableFuture.whenComplete((result, ex) -> {
            if (ex == null) {
                log.info("Kafka message successfully sent on topic {} and value {}",
                        topicName, result.getProducerRecord().value());
            } else {
                log.error("An error occurred while sending kafka message for event with value {}", producerRecord);
            }
        });
    }
}