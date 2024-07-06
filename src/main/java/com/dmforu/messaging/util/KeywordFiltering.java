package com.dmforu.messaging.util;

import com.dmforu.subscribe.config.Keywords;

import java.util.HashSet;
import java.util.Set;

public class KeywordFiltering {


    //Keyword 필터링 [ 예 : 중간고사 -> 시험, 기말고사 -> 시험 ]
    public static Set<String> keywordFilter(String title) {
        Set<String> keywordList = new HashSet<>();
          for (Keywords value : Keywords.values()) {
                if (title.contains(value.getKoreanName())) {
                    keywordList.add(value.getKoreanName());
                }
            }
        if (title.contains("중간고사") || title.contains("기말고사"))
            keywordList.add("시험");

        return keywordList;
    }

}