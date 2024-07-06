package com.dmforu.v2_messaging.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
public class NoticeDtoV2 {

    private String type;
    private LocalDate date;
    private String title;
    private String author;
    private String url;

}