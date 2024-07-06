package com.dmforu.v2_subscrible.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class KeywordDTO {

    private String token;
    private List<String> keywordsList;

}
