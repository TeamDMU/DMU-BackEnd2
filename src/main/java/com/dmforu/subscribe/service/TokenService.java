package com.dmforu.subscribe.service;

import com.dmforu.subscribe.dto.NoticeMapper;
import com.dmforu.subscribe.dto.RequestTokenDto;
import com.dmforu.subscribe.dto.TokensDto;
import com.dmforu.subscribe.token.Token;
import com.google.firebase.messaging.FirebaseMessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final Token<TokensDto> googleToken;
    private final Token<TokensDto> redisToken;

    //토큰 생명주기 연장
    public void refreshToken(RequestTokenDto tokensDto) {
        redisToken.refreshToken(mapToRequestTokenDto(tokensDto));
    }


    //토픽 업데이트
//    @Async("customThreadPool")
    public void updateTopic(RequestTokenDto tokensDto) throws FirebaseMessagingException, ExecutionException, InterruptedException {
        redisToken.updateToken(mapToRequestTokenDto(tokensDto));
        googleToken.updateToken(mapToRequestTokenDto(tokensDto));
    }

    public void deleteTopic(RequestTokenDto tokensDto) {
        redisToken.deleteToken(mapToRequestTokenDto(tokensDto));
        googleToken.deleteToken(mapToRequestTokenDto(tokensDto));
    }

    private TokensDto mapToRequestTokenDto(RequestTokenDto requestTokenDto) {
        return NoticeMapper.RequestTokenDtoToServiceTokensDto(requestTokenDto);
    }
}