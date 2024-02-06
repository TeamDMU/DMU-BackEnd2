package me.gijung.DMforU.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentNotice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 게시글 번호
    int number;

    // 학과명
    String department;

    // 작성 날짜
    LocalDate date;

    // 게시글 제목
    String title;

    // 게시글 작성자
    String author;

    // 게시글 URL
    String url;
}
