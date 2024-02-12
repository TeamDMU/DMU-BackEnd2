package me.gijung.DMforU.repository;

import me.gijung.DMforU.model.entity.DepartmentNotice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DepartmentNoticeRepository extends JpaRepository<DepartmentNotice, Long> {

    /** 학과의 가장 최신 공지사항 번호를 확인하는 메서드
     *
     * @Param 학과 이름
     * @Return 해당 학과의 최신 공지사항 번호, 만약 공지사항이 존재하지 않다면 Null을 반환한다.*/
    @Query("SELECT MAX(e.number) FROM DepartmentNotice e WHERE e.department = :departmentName")
    Integer findMaxNumberByDepartmentName(@Param("departmentName") String departmentName);

    /**
     * 학과의 공지사항을 페이징네이션하는 메서드
     *
     * @param department 학과 이름
     * @param pageable 페이지 단위
     * @return 학과의 공지사항을 페이지 단위에 맞게 반환한다.
     */
    Page<DepartmentNotice> findByDepartment(String department, Pageable pageable);
}
