package com.dmforu.messaging.util;

import com.dmforu.messaging.dto.MessageDtoV2;
import com.google.firebase.messaging.MulticastMessage;
import com.google.firebase.messaging.Notification;

import java.util.List;

public class MessagingV2 {

    /**
     * 공통된 MulticastMessage 빌더 메서드
     * @param tokenList FCM 토큰 리스트
     * @param title     제목
     * @param content   내용
     * @return MulticastMessage.Builder
     */
    private static MulticastMessage.Builder createBaseMessage(List<String> tokenList, String title, String content) {
        return MulticastMessage.builder()
                .setNotification(Notification.builder()
                        .setTitle(title)
                        .setBody(content)
                        .build())
                .addAllTokens(tokenList);
    }

    /**
     * 기본 Message 생성 유틸 메서드
     * @param tokenList FCM 토큰 리스트
     * @param title     제목
     * @param content   내용
     * @return MulticastMessage
     */
    public static MulticastMessage buildMessage(List<String> tokenList, String title, String content) {
        return createBaseMessage(tokenList, title, content).build();
    }


    /**
     * URL과 Type을 포함한 Message 생성 유틸 메서드
     * @param messageDtoV2  메시지 DTO
     * @param tokenList     FCM 토큰 리스트
     * @param type          메시지 타입
     * @return MulticastMessage
     */
    public static MulticastMessage buildMessage(MessageDtoV2 messageDtoV2, List<String> tokenList, String type) {
        return createBaseMessage(tokenList, messageDtoV2.getTitle(), messageDtoV2.getBody())
                .putData("url", messageDtoV2.getUrl())
                .putData("type", type)
                .build();
    }
}