package me.gijung.DMforU.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UniversityNotice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 게시글 번호
    private int number;

    // 작성 날짜
    private LocalDate date;

    // 게시글 제목
    private String title;

    // 게시글 작성자
    private String author;

    // 게시글 URL
    private String url;
}
