package me.gijung.DMforU.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
public class TypeNoticeDto {

    private LocalDate date;
    private String title;
    private String author;
    private String url;
    private String type;

}
