package com.agree.chattingapi.services;

import ch.qos.logback.classic.Logger;
import com.agree.chattingapi.entities.UserInfo;
import com.agree.chattingapi.repositories.UserRepository;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import jakarta.annotation.PostConstruct;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class FirebaseService {
    private static final Logger log = (Logger) LoggerFactory.getLogger(FirebaseService.class);

    private final UserRepository userRepository;

    public FirebaseService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void onStart() {
        if (FirebaseApp.getApps().isEmpty()) {
            try {
                InputStream serviceAccount = getClass().getClassLoader().getResourceAsStream("serviceAccountKey.json");

                FirebaseOptions options = new FirebaseOptions.Builder()
                        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                        .build();

                FirebaseApp.initializeApp(options);
            } catch (Exception e) {
                log.error("Error initializing Firebase", e);
            }
        }
    }

    public void sendMessage(String title, String body, String to) throws FirebaseMessagingException {
        UserInfo findUser = userRepository.findById(to).orElseThrow();

        String token = findUser.getPushKey();

        Notification notification = Notification
                .builder()
                .setTitle(title)
                .setBody(body)
                .build();

        Message message = Message.builder()
                .setNotification(notification)
                .setToken(token)
                .build();

        String messageId = FirebaseMessaging.getInstance().send(message);
        log.debug("Sent message with ID: " + messageId);

    }

}
