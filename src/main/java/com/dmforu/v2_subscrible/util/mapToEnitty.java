package com.dmforu.v2_subscrible.util;

import com.dmforu.v2_subscrible.model.dto.InitTokensDTO;
import com.dmforu.v2_subscrible.model.entity.Token;

public class mapToEnitty {


    public static Token tokenDtoMapToEntity(InitTokensDTO initTokensDto) {
        return Token.builder()
                .token(initTokensDto.getToken())
                .department(initTokensDto.getDepartment())
                .keywordsList(initTokensDto.getKeywordsList())
                .keywordOnOFF(initTokensDto.getKeywordOnOFF())
                .departmentOnOFF(initTokensDto.getDepartmentOnOFF())
                .build();
    }

}
