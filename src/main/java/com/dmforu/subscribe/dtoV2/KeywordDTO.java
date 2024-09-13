package com.dmforu.subscribe.dtoV2;

import com.dmforu.subscribe.config.Topic;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class KeywordDTO {

    private String token;
    private List<String> keywordsList;

    public KeywordDTO(String token, List<Topic> keywordsList) {
        List<String> newKeywordList = new ArrayList<>();
        for (Topic topic : keywordsList) {
            newKeywordList.add(topic.getKoreanName());
        }

        this.token = token;
        this.keywordsList = newKeywordList;
    }
}
