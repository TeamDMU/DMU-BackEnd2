package me.gijung.DMforU.service.Mesaage;

import com.google.firebase.messaging.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class Messaging implements FirebaseMessagingService<FirebaseMessaging>{
    @Override
    public FirebaseMessaging getInstance() {
        return FirebaseMessaging.getInstance();
    }

    public Message sendMessage(String topic, String title, String body) {
        return Message.builder()
                .setTopic(topic)
                .setNotification(Notification.builder()
                        .setTitle(title)
                        .setBody(body)
                        .build())
                .setAndroidConfig(AndroidConfig.builder()
                        .setNotification(AndroidNotification.builder()
                                .setChannelId("high_importance_channel")//프론트랑 맞춰야댐
                                .build())
                        .build()
                )
                .build();
    }
}
