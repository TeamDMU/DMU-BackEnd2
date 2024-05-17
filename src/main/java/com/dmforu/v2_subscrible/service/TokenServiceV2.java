package com.dmforu.v2_subscrible.service;

import com.dmforu.v2_subscrible.model.dto.InitTokensDTO;
import com.dmforu.v2_subscrible.model.dto.KeywordDTO;
import com.dmforu.v2_subscrible.model.dto.KeywordStatusDTO;
import com.dmforu.v2_subscrible.token.Redis;
import com.dmforu.v2_subscrible.token.TokenImplInformation;
import com.google.firebase.messaging.FirebaseMessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class TokenServiceV2 {

    private final TokenImplInformation tokenImplInformation;
    private final Redis redisTokenV2Impl;
//
//    //토큰 생명주기 연장
//    public void refreshToken(RequestTokenDto tokensDto) {
////        redisToken.refreshToken(mapToRequestTokenDto(tokensDto));
//    }
    
    // Token 신규 정보 생성
    public void createInitTokenInformation(InitTokensDTO initTokensDto) {
        tokenImplInformation.createInitTokenInformation(initTokensDto);
//        redisTokenV2Impl.createToken(repectorTokensDto.getToken());
    }

    //키워드 업데이트
    public void updateKeyword(KeywordDTO keywordDto) {
        tokenImplInformation.updateKeyword(keywordDto);
    }

    //키워드 상태 업데이트 [ ON OFF ]
    public void updateKeyWordStatus(KeywordStatusDTO keywordStatusDTO) {
        tokenImplInformation.updateKeywordStatus(keywordStatusDTO);
    }


}