package com.dmforu.v2_messaging.util;

import com.dmforu.v2_messaging.dto.MessageDtoV2;
import com.google.firebase.messaging.MulticastMessage;
import com.google.firebase.messaging.Notification;

import java.util.List;

public class MessagingV2 {

    //학과 메시지 툴 작성
    public static MulticastMessage buildMessage(MessageDtoV2 messageDtoV2, List<String> tokenList, String type) {
        return MulticastMessage.builder()
                .setNotification(Notification.builder()
                        .setTitle(messageDtoV2.getTitle())
                        .setBody(messageDtoV2.getBody())
                        .build())
                .addAllTokens(tokenList)
                .putData("url", messageDtoV2.getUrl())
                .putData("type", type)
                .build();
    }

    //대학 메시지 툴 작성
    public static MulticastMessage buildMessage(MessageDtoV2 messageDtoV2, List<String> tokenList, String type, String keyWordList) {
        return MulticastMessage.builder()
                .setNotification(Notification.builder()
                        .setTitle(messageDtoV2.getTitle())
                        .setBody(messageDtoV2.getBody())
                        .build())
                .addAllTokens(tokenList)
                .putData("url", messageDtoV2.getUrl())
                .putData("type", type)
                .putData("keyWordList", keyWordList)
                .build();
    }
}