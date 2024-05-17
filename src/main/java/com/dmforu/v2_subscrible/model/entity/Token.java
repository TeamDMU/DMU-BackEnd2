package com.dmforu.v2_subscrible.model.entity;

import com.dmforu.v2_subscrible.util.StringListConvertor;
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

    private String department;

    @Convert(converter = StringListConvertor.class)
    @Column(length = 1000)
//    @ElementCollection
    private List<String> keywordsList;

    private int departmentOnOFF;

    private int keywordOnOFF;


    @Builder
    public Token(String token, String department, List<String> keywordsList, int departmentOnOFF, int keywordOnOFF) {

        this.token = token;
        this.department = department;
        this.keywordsList = keywordsList;
        this.departmentOnOFF = departmentOnOFF;
        this.keywordOnOFF = keywordOnOFF;

    }

    public void updateKeywords(List<String> keywordsList) {
        this.keywordsList = keywordsList;
    }

    public void updateDepartmentStatus(int OnOFF) {
        this.departmentOnOFF = OnOFF;
    }

    public void updateKeywordStatus(int OnOFF) {
        this.keywordOnOFF = OnOFF;
    }
}
