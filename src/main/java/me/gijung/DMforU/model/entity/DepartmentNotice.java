package me.gijung.DMforU.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.gijung.DMforU.service.Listener.DepartmentNoticeListener;

import java.time.LocalDate;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EntityListeners(DepartmentNoticeListener.class)
public class DepartmentNotice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 게시글 번호
    private int number;

    // 학과명
    private String department;

    // 작성 날짜
    private LocalDate date;

    // 게시글 제목
    private String title;

    // 게시글 작성자
    private String author;

    // 게시글 URL
    private String url;

}
