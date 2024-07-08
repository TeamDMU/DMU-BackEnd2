package com.dmforu.subscribe.dtoV2;

import com.dmforu.subscribe.config.Topic;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
//최초 등록때 쓰이는 DTO ( Token 정보 )
public class InitTokensDTO {

    private String token;
    private String department;
    private boolean departmentOnOFF;
    private boolean keywordOnOFF;
    private List<String> keywordsList;

    public InitTokensDTO() {}

    public InitTokensDTO(String token, String department, List<Topic> keywordsList, boolean departmentOnOFF, boolean keywordOnOFF) {
        List<String> keywordList = new ArrayList<>();
        for (Topic t : keywordsList) {
            keywordList.add(t.getKoreanName());
        }

        this.token = token;
        this.department = department;
        this.keywordsList = keywordList;
        this.departmentOnOFF = departmentOnOFF;
        this.keywordOnOFF = keywordOnOFF;
    }
}
