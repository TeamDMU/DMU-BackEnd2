package com.dmforu.subscribe.util;

import com.dmforu.subscribe.dtoV2.InitTokensDTO;
import com.dmforu.subscribe.entity.Token;

public class mapToEnitty {
    public static Token tokenDtoMapToEntity(InitTokensDTO initTokensDto) {
        return Token.builder()
                .token(initTokensDto.getToken())
                .department(initTokensDto.getDepartment())
                .keywordsList(initTokensDto.getKeywordsList())
                .keywordOnOFF(initTokensDto.isKeywordOnOFF())
                .departmentOnOFF(initTokensDto.isDepartmentOnOFF())
                .build();
    }
}
