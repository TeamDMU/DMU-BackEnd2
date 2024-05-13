package com.dmforu.notice;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 공지사항 번호
    private int number;

    // 공지사항 종류
    private String type;

    // 공지사항 날짜
    private LocalDate date;

    // 공지사항 제목
    private String title;

    // 공지사항 작성자
    private String author;

    // 공지사항 URL
    private String url;
}
