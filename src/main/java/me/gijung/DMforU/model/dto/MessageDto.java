package me.gijung.DMforU.model.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class MessageDto {

    private final String setTitle = "키워드 알림 도착";
    private final String setBody = " 키워드가 포함된 공지가 기다리고 있어요!";

    private String topic;
    private String body;

    @Builder
    public MessageDto(String topic, String Inbody) {
        this.topic = topic;
        this.body = Inbody + setBody;
    }
}
