package me.gijung.DMforU.repository;

import me.gijung.DMforU.model.entity.DepartmentNotice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface NoticeRepository extends JpaRepository<DepartmentNotice, Long> {

    /**
     * 학과, 대학 공지사항을 검색하는 메서드 <br>
     * 페이지네이션을 적용하여 페이지 단위에 알맞게 반환한다.
     *
     * @param keyword  검색할 키워드
     * @param pageable 페이지 단위
     * @return 키워드에 맞는 공지사항 페이지
     */
    @Query(value = "SELECT * FROM ( " +
            "SELECT date, title, author, url FROM department_notice " +
            "UNION " +
            "SELECT date, title, author, url FROM university_notice " +
            ") AS subquery " +
            "WHERE REPLACE(title, ' ', '') LIKE CONCAT('%', REPLACE(:keyword, ' ', ''), '%') " +
            "ORDER BY date DESC",
            countQuery = "SELECT COUNT(*) FROM ( " +
                    "SELECT date, title, author, url FROM department_notice " +
                    "UNION " +
                    "SELECT date, title, author, url FROM university_notice " +
                    ") AS subquery " +
                    "WHERE REPLACE(title, ' ', '') LIKE CONCAT('%', REPLACE(:keyword, ' ', ''), '%') ",
            nativeQuery = true)
    Page<Object> searchByTitleIgnoringSpaces(@Param("keyword") String keyword, Pageable pageable);
}
