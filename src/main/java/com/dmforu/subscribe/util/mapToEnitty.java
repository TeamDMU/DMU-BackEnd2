package com.dmforu.subscribe.util;

import com.dmforu.subscribe.dtoV2.InitTokensDTO;
import com.dmforu.subscribe.entity.Token;

public class mapToEnitty {
    public static Token tokenDtoMapToEntity(InitTokensDTO initTokensDto) {

        if (initTokensDto.getKeywordsList() == null || initTokensDto.getKeywordsList().isEmpty()) {
            return Token.builder()
                    .token(initTokensDto.getToken())
                    .department(initTokensDto.getDepartment())
                    .keywordsList(null)
                    .keywordOnOFF(initTokensDto.isKeywordOnOFF())
                    .departmentOnOFF(initTokensDto.isDepartmentOnOFF())
                    .build();
        }

        return Token.builder()
                .token(initTokensDto.getToken())
                .department(initTokensDto.getDepartment())
                .keywordsList(initTokensDto.getKeywordsList().isEmpty() ? null : initTokensDto.getKeywordsList())
                .keywordOnOFF(initTokensDto.isKeywordOnOFF())
                .departmentOnOFF(initTokensDto.isDepartmentOnOFF())
                .build();
    }
}
