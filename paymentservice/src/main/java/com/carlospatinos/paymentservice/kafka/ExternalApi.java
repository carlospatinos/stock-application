package com.carlospatinos.paymentservice.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.carlospatinos.paymentservice.model.NotificationMessage;
import com.carlospatinos.stockservice.StockMessage;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class ExternalApi {
    @Autowired
    private RestTemplate restTemplate;

    @Value(value = "${notificationservice.submission.url}")
    private String submissionUrl;

    @CircuitBreaker(name = "notificationServiceCircuitBreaker", fallbackMethod = "circuitOpen")
    public void sendNotification(StockMessage stockMessage) throws Exception {
        log.info("Trying to call external service: {}", stockMessage);
        NotificationMessage message = new NotificationMessage(stockMessage.getUser(),
                "Congratulations",
                "New stock [" + stockMessage.getName() + "] in your portfolio.");

        ResponseEntity<String> result = restTemplate.postForEntity(submissionUrl,
                message, String.class);
        log.info("Result: {}", result);
    }

    public void circuitOpen(Throwable throwable) {
        log.error("External API is down, using alternative method.");
    }
}
