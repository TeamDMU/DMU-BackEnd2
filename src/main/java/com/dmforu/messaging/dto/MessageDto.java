package com.dmforu.messaging.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class MessageDto {

    private final static String TITLE_MESSAGE = " 키워드 알림 도착";

    private String title;
    private String topic;
    private String body;
    private String url;

    //대학 공지사항
    public MessageDto(TypeNoticeDto typeNoticeDto, String topic, String title) {
        this.title = builderTitle(title);
        this.topic = topic;
        this.body = typeNoticeDto.getTitle();
        this.url = typeNoticeDto.getUrl();
    }

    //학과 공지사항
    public MessageDto(TypeNoticeDto typeNoticeDto) {
        this.title = builderTitle(typeNoticeDto.getType());
        this.body = typeNoticeDto.getTitle();
        this.url = typeNoticeDto.getUrl();
    }

    private String builderTitle(String message) {
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        sb.append(message);
        sb.append(" ] ");
        sb.append(TITLE_MESSAGE);
        return sb.toString();
    }
}
