package com.carlospatinos.notificationservice.service;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carlospatinos.notificationservice.db.model.UserToken;
import com.carlospatinos.notificationservice.db.repository.UserTokenRepository;
import com.carlospatinos.notificationservice.model.NotificationRequest;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FCMService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserTokenRepository userTokenRepository;

    @PostConstruct
    public void initialize() {
    }

    private String sendAndGetResponse(Message message) throws InterruptedException, ExecutionException {
        return FirebaseMessaging.getInstance().sendAsync(message).get();
    }

    public void sendMessageToToken(NotificationRequest request) throws InterruptedException, ExecutionException {
        Notification notification = Notification.builder().setTitle(request.getTitle()).setBody(request.getBody())
                .build();

        List<UserToken> persistedUsers = userTokenRepository.findByAppUser(request.getUser());

        for (UserToken userToken : persistedUsers) {
            log.info("Sending mesasge to persistedUser {}", userToken);

            Message message = null;
            if (userToken != null && userToken.getAppToken() != null) {
                message = Message.builder().setNotification(notification).setToken(userToken.getAppToken()).build();
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
    }

    public void subscribeNotification(NotificationRequest request) {
        log.info("request: {}", request);

        UserToken userToken = new UserToken();
        userToken.setAppToken(request.getToken());
        userToken.setAppUser(request.getUser());
        userTokenRepository.save(userToken);

    }
}
