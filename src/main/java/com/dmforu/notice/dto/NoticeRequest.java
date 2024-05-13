package com.dmforu.notice.dto;

import com.dmforu.notice.Notice;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Builder
@Getter
@AllArgsConstructor
public class NoticeRequest {

    @Schema(description = "날짜", example = "2024-05-09")
    private LocalDate date;

    @Schema(description = "날짜", example = "시흥시인재양성재단 24년 상반기 장학생 선발 안내")
    private String title;

    @Schema(description = "작성자", example = "최지예")
    private String author;

    @Schema(description = "URL", example = "https://www.dongyang.ac.kr/bbs/dongyang/7/124420/artclView.do?layout=unknown")
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