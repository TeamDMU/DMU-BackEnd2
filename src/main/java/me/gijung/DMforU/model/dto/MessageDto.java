package me.gijung.DMforU.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
public class
MessageDto {
    private String topic;
    private String title;
    private String body;
    public MessageDto(String topic, String body) {
        this.topic = topic;
        this.title = "키워드 알림 도착";
        this.body ="'" + body + "'" + "키워드가 포함된 공지가 기다리고 있어요!";
    }
}
