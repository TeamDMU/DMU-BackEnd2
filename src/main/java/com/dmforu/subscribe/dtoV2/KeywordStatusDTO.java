package com.dmforu.subscribe.dtoV2;

import lombok.Data;

import java.util.List;

@Data
public class KeywordStatusDTO {

    private String token;
    private List<String> keywordsList;
    private boolean keywordOnOFF;

}
