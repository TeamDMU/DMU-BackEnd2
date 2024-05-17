package com.dmforu.v2_messaging.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class MessageDtoV2 {

    private final static String TITLE_MESSAGE = " 키워드 알림 도착";

    private String title;
    private String keyword;
    private String body;
    private String url;
    private String type;

    //대학 공지사항
    public MessageDtoV2(NoticeDtoV2 noticeDtoV2, String keyword) {
        this.title = builderTitle(noticeDtoV2.getType());
        this.keyword = keyword;
        this.body = noticeDtoV2.getTitle();
        this.url = noticeDtoV2.getUrl();
        this.type = noticeDtoV2.getType();
    }

    //학과 공지사항
    public MessageDtoV2(NoticeDtoV2 noticeDtoV2) {
        this.title = builderTitle(noticeDtoV2.getType());
        this.body = noticeDtoV2.getTitle();
        this.url = noticeDtoV2.getUrl();
        this.type = noticeDtoV2.getType();
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
