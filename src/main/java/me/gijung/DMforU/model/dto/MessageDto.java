package me.gijung.DMforU.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class MessageDto {

    private final static String TITLE_MESSAGE = "키워드 알림 도착";

    private String topic;
    private String title;
    private String body;

    // TODO: String 문자열 final static 으로 수정하기
    public MessageDto(String topic, String body) {
        this.topic = topic;
        this.title = TITLE_MESSAGE;
        this.body ="'" + body + "'" + " 키워드가 포함된 공지가 기다리고 있어요!";
    }
}
