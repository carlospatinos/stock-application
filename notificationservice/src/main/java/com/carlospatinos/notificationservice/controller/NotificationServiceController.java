package com.carlospatinos.notificationservice.controller;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.carlospatinos.notificationservice.model.NotificationRequest;
import com.carlospatinos.notificationservice.model.NotificationResponse;
import com.carlospatinos.notificationservice.service.FCMService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class NotificationServiceController {

    @Autowired
    private FCMService fcmService;

    @GetMapping("/health")
    public ResponseEntity<String> health() throws InterruptedException, ExecutionException {
        log.info("Service working");
        return ResponseEntity.ok("Service working");
    }

    @PostMapping("/notification")
    public ResponseEntity sendNotification(@RequestBody NotificationRequest request)
            throws InterruptedException, ExecutionException {

        log.info("request: ", request);

        fcmService.sendMessageToToken(request);
        return new ResponseEntity<>(new NotificationResponse(HttpStatus.OK.value(), "Notification has been sent"),
                HttpStatus.OK);
    }

    @PostMapping("/submission")
    public ResponseEntity<?> sendNotification2(@RequestBody NotificationRequest request)
            throws InterruptedException, ExecutionException {

        fcmService.sendMessageToToken(request);

        return ResponseEntity.ok("Service working");

    }

    @PostMapping("/subscription")
    public ResponseEntity<?> subscribe(@RequestBody NotificationRequest request)
            throws InterruptedException, ExecutionException {

        fcmService.subscribeNotification(request);

        return ResponseEntity.ok("Service working");

    }

}
