package me.gijung.DMforU.service.Mesaage;

import com.google.firebase.messaging.*;
import lombok.RequiredArgsConstructor;
import me.gijung.DMforU.model.dto.MessageDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class Messaging implements FirebaseMessagingService<FirebaseMessaging>{
    @Override
    public FirebaseMessaging getInstance() {
        return FirebaseMessaging.getInstance();
    }

    public Message sendMessage(MessageDto messageDto) {
        return Message.builder()
                .setTopic(messageDto.getTopic())
                .setNotification(Notification.builder()
                        .setTitle(messageDto.getTitle())
                        .setBody(messageDto.getBody())
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
