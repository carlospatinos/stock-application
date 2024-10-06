package com.carlospatinos.notificationservice.service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.carlospatinos.notificationservice.model.NotificationRequest;
import com.carlospatinos.notificationservice.model.UserTokenRecord;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FCMService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    private Map<String, UserTokenRecord> userTokenRegistry;

    @PostConstruct
    public void initialize() {
        userTokenRegistry = new HashMap<>();
    }

    private String sendAndGetResponse(Message message) throws InterruptedException, ExecutionException {
        return FirebaseMessaging.getInstance().sendAsync(message).get();
    }

    public void sendMessageToToken(NotificationRequest request) throws InterruptedException, ExecutionException {
        Notification notification = Notification.builder().setTitle(request.getTitle()).setBody(request.getBody())
                .build();

        // TODO send the message to saved token
        UserTokenRecord savedUser = userTokenRegistry.get(request.getUser());
        log.info("savedUser: {}", savedUser);

        Message message = null;
        if (savedUser != null && savedUser.getToken() != null) {
            message = Message.builder().setNotification(notification).setToken(savedUser.getToken()).build();
        } else if (request != null && request.getToken() != null) {
            message = Message.builder().setNotification(notification).setToken(request.getToken()).build();
        } else {
            logger.error("Token mapping for user [{}] not found", request.getUser());
            return;
        }

        // Gson gson = new GsonBuilder().setPrettyPrinting().create();
        // String jsonOutput = gson.toJson(message);
        String response = sendAndGetResponse(message);
        logger.info("Sent message response {}", response);
    }

    public void subscribeNotification(NotificationRequest request) {
        log.info("request: {}", request);
        userTokenRegistry.put(request.getUser(),
                new UserTokenRecord(request.getUser(),
                        request.getToken()));
    }
}
