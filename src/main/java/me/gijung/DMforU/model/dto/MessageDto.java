package me.gijung.DMforU.model.dto;

import lombok.*;
import lombok.Builder;
@Builder
@Getter
@AllArgsConstructor
public class MessageDto {

    private final static String TITLE_MESSAGE = " 키워드 알림 도착";

    private String title;
    private String topic;
    private String body;


    //대학 공지사항
    public MessageDto(TypeNoticeDto typeNoticeDto,String topic, String title) {
        this.title = builder_title(title);
        this.topic = topic;
        this.body = typeNoticeDto.getTitle();
    }

    //학과 공지사항
    public MessageDto(TypeNoticeDto typeNoticeDto) {
        this.title = builder_title(typeNoticeDto.getType());
        this.body = typeNoticeDto.getTitle();
    }

    private String builder_title(String message) {
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        sb.append(message);
        sb.append(" ] ");
        sb.append(TITLE_MESSAGE);
        return sb.toString();
    }
}
