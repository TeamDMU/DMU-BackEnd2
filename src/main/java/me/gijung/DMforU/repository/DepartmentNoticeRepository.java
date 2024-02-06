package me.gijung.DMforU.repository;

import me.gijung.DMforU.model.domain.DepartmentNoticeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DepartmentNoticeRepository extends JpaRepository<DepartmentNoticeEntity, Long> {

    /** 학과의 가장 최신 게시글 번호를 확인하는 메서드
     * @Param: 학과 이름
     * @Return: 해당 학과의 최신 게시글 번호*/
    @Query("SELECT MAX(e.number) FROM DepartmentNoticeEntity e WHERE e.department = :departmentName")
    Long findMaxNumberByDepartmentName(@Param("departmentName") String departmentName);
}
