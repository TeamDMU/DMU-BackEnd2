package me.gijung.DMforU.model.dto;

import lombok.Builder;
import lombok.Getter;
import me.gijung.DMforU.model.entity.Notice;

import java.time.LocalDate;

@Getter
@Builder
public class DepartmentNoticeDto {

    private LocalDate date;
    private String title;
    private String author;
    private String url;
    private String type;

}
