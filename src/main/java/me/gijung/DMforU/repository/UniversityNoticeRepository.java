package me.gijung.DMforU.repository;

import me.gijung.DMforU.model.entity.UniversityNotice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UniversityNoticeRepository extends JpaRepository<UniversityNotice, Long> {

    /**
     * 가장 최신 대학 공지사항 번호를 확인하는 메서드
     *
     * @return 최신 대학 공지사항 번호, 만약 공지사항이 존재하지 않다면 Null을 반환
     */
    @Query("SELECT MAX(e.number) FROM UniversityNotice e")
    Integer findByMaxNumber();
}
