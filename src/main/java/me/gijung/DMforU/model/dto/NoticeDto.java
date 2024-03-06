package me.gijung.DMforU.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import me.gijung.DMforU.model.entity.Notice;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
public class NoticeDto {

    private LocalDate date;
    private String title;
    private String author;
    private String url;

    public NoticeDto(Notice notice) {
        this.title = notice.getTitle();
        this.date = notice.getDate();
        this.author = notice.getAuthor();
        this.url = notice.getUrl();
    }
}
