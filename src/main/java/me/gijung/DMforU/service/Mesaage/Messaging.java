package me.gijung.DMforU.service.Mesaage;

import com.google.firebase.messaging.*;
import lombok.RequiredArgsConstructor;
import me.gijung.DMforU.model.dto.MessageDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class Messaging implements FirebaseMessagingService<FirebaseMessaging>{
    @Override
    public FirebaseMessaging getInstance() {
        return FirebaseMessaging.getInstance();
    }

    public static Message sendMessage(MessageDto messageDto) {
        return Message.builder()
                .setTopic(messageDto.getTopic())
                .setNotification(Notification.builder()
                        .setTitle(messageDto.getTitle())
                        .setBody(messageDto.getBody())
                        .build())
                .build();
    }

    public static MulticastMessage sendMessage(Set<String> tokenList) {
        return MulticastMessage.builder()
                .setNotification(Notification.builder()
                        .setTitle("학과 알림 도착")
                        .setBody("학과 공지가 기다리고 있어요!")
                        .build())
                .addAllTokens(tokenList)
                .build();
    }
}
