package com.carlospatinos.notificationservice.service;

import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.carlospatinos.notificationservice.model.NotificationRequest;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;

@Service
public class FCMService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    private String sendAndGetResponse(Message message) throws InterruptedException, ExecutionException {
        return FirebaseMessaging.getInstance().sendAsync(message).get();
    }

    public void sendMessageToToken(NotificationRequest request) throws InterruptedException, ExecutionException {
        Notification notification = Notification.builder().setTitle(request.getTitle()).setBody(request.getBody())
                .build();
        Message message = Message.builder().setNotification(notification).setToken(request.getToken()).build();

        // Gson gson = new GsonBuilder().setPrettyPrinting().create();
        // String jsonOutput = gson.toJson(message);
        String response = sendAndGetResponse(message);
        logger.info("Sent message response {}", response);
    }
}
