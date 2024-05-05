package com.dmforu.notice.dto;

import com.dmforu.notice.Notice;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Builder
@Getter
@AllArgsConstructor
public class NoticeRequest {

    private LocalDate date;
    private String title;
    private String author;
    private String url;

    public static NoticeRequest toDto(Notice notice) {
        return NoticeRequest.builder()
                .date(notice.getDate())
                .title(notice.getTitle())
                .author(notice.getAuthor())
                .url(notice.getUrl())
                .build();
    }
}