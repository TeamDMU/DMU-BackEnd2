package com.dmforu.v2_subscrible.repository;

import com.dmforu.v2_subscrible.model.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;


public interface TokenRepository extends JpaRepository<Token, String> {

    @Query("SELECT t FROM Token t WHERE t.departmentOnOFF = 1 AND t.department = :department")
    List<Token> getDepartment(@Param("department") String department);

    @Query(value = "SELECT * FROM token WHERE keywords_list LIKE %:keyword%", nativeQuery = true)
    List<Token> findByTokenInKeyword(@Param("keyword") String keyword);
}
