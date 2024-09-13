package com.dmforu.messaging;

import com.dmforu.messaging.dto.MessageDto;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.MulticastMessage;
import com.google.firebase.messaging.Notification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class Messaging{

    //대학공지
    public static Message buildMessage(MessageDto messageDto) {
        return Message.builder()
                .setTopic(messageDto.getTopic())
                .setNotification(Notification.builder()
                        .setTitle(messageDto.getTitle())
                        .setBody(messageDto.getBody())
                        .build())
                .putData("url", messageDto.getUrl())
                .build();
    }

    //학과공지
    public static MulticastMessage buildMessage(MessageDto messageDto, List<String> tokenList) {
        return MulticastMessage.builder()
                .setNotification(Notification.builder()
                        .setTitle(messageDto.getTitle())
                        .setBody(messageDto.getBody())
                        .build())
                .addAllTokens(tokenList)
                .putData("url", messageDto.getUrl())
                .build();
    }
}