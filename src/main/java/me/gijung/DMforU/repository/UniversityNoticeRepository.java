package me.gijung.DMforU.repository;

import me.gijung.DMforU.model.entity.UniversityNotice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UniversityNoticeRepository extends JpaRepository<UniversityNotice, Long> {

    @Query("SELECT MAX(e.number) FROM UniversityNotice e")
    Integer findByMaxNumber();
}
