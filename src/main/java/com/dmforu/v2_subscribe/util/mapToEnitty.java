package com.dmforu.v2_subscribe.util;

import com.dmforu.v2_subscribe.model.dto.InitTokensDTO;
import com.dmforu.v2_subscribe.model.entity.Token;

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
