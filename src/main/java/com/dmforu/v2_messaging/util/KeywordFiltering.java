package com.dmforu.v2_messaging.util;

import com.dmforu.v2_subscrible.config.Keywords;

public class KeywordFiltering {


    //Keyword 필터링 [ 예 : 중간고사 -> 시험, 기말고사 -> 시험 ]
    public static String keywordFilter(String title) {
        if (title.contains("중간고사") || title.contains("기말고사")) {
            return "시험";
        }else{
            for (Keywords value : Keywords.values()) {
                if (title.contains(value.getKoreanName())) {
                    return value.getKoreanName();
                }
            }
        }
        return "none";
    }

}
