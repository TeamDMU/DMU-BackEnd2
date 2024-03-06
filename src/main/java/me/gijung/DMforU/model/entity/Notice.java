package me.gijung.DMforU.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.gijung.DMforU.service.Listener.NoticeListener;

import java.time.LocalDate;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
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
