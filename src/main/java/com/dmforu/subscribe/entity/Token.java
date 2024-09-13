package com.dmforu.subscribe.entity;

import com.dmforu.subscribe.dtoV2.KeywordStatusDTO;
import com.dmforu.subscribe.util.StringListConvertor;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@DynamicUpdate
public class Token {

    @Id
    private String token;

    @Column(nullable = true)
    private String department;

    @Convert(converter = StringListConvertor.class)
    @Column(length = 1000, nullable = true)
    private List<String> keywordsList;

    @Column(nullable = false)
    private boolean departmentOnOFF;

    @Column(nullable = false)
    private boolean keywordOnOFF;


    @Builder
    public Token(String token, String department, List<String> keywordsList, boolean departmentOnOFF, boolean keywordOnOFF) {
        this.token = token;
        this.department = department;
        this.keywordsList = keywordsList;
        this.departmentOnOFF = departmentOnOFF;
        this.keywordOnOFF = keywordOnOFF;
    }

    public void updateKeywords(List<String> keywordsList) {
        this.keywordsList = keywordsList;
    }

    public void updateDepartmentStatus(boolean OnOFF) {
        this.departmentOnOFF = OnOFF;
    }

    public void updateKeywordStatus(KeywordStatusDTO keywordStatusDTO) {
//        this.keywordsList = keywordStatusDTO.getKeywordsList();
        this.keywordOnOFF = keywordStatusDTO.isKeywordOnOFF();
    }
    public void updateDepartment(String department) {
        this.department = department;
    }
}
