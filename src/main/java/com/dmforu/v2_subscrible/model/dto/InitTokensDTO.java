package com.dmforu.v2_subscrible.model.dto;

import lombok.Data;

import java.util.List;

@Data
//최초 등록때 쓰이는 DTO ( Token 정보 )
public class InitTokensDTO {

    private String token;
    private String department;
    private List<String> keywordsList;
    private int departmentOnOFF;
    private int keywordOnOFF;

}
