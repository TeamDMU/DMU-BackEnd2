package com.dmforu.v2_subscribe.repository;

import com.dmforu.v2_subscribe.model.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface TokenRepository extends JpaRepository<Token, String> {

    @Query("SELECT t.token FROM Token t WHERE t.departmentOnOFF = true AND t.department = :department")
    List<String> getDepartment(@Param("department") String department);

    @Query(value = "SELECT token.token FROM token WHERE keyword_onoff = true AND keywords_list LIKE %:keyword%", nativeQuery = true)
    List<String> findByTokenInKeyword(@Param("keyword") String keyword);

}
