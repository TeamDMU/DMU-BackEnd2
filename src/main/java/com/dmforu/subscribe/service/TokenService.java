package com.dmforu.subscribe.service;

import com.dmforu.subscribe.dto.NoticeMapper;
import com.dmforu.subscribe.dto.RequestTokenDto;
import com.dmforu.subscribe.dto.TokensDto;
import com.dmforu.subscribe.dto.TopicRequestDto;
import com.dmforu.subscribe.token.GoogleToken;
import com.dmforu.subscribe.token.RedisToken;
import com.dmforu.subscribe.token.Token;
import com.google.firebase.messaging.FirebaseMessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final GoogleToken googleToken;
    private final RedisToken redisToken;

    //토큰 생명주기 연장
    public void refreshToken(String token) {
        redisToken.refreshToken(token);
    }

    public void createTopic(TokensDto tokensDto) {
        redisToken.createToken(tokensDto);
        googleToken.createToken(tokensDto);
    }

    //토픽 업데이트
//    @Async("customThreadPool")
    public void updateTopic(TokensDto tokensDto) throws FirebaseMessagingException {
        redisToken.updateToken(tokensDto);
        googleToken.updateToken(tokensDto);
    }

    public void deleteTopic(String token) {
        redisToken.deleteToken(token);
        googleToken.deleteToken(token);
    }

}