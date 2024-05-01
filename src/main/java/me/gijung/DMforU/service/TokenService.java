package me.gijung.DMforU.service;

import com.google.firebase.messaging.FirebaseMessagingException;
import lombok.RequiredArgsConstructor;
import me.gijung.DMforU.model.dto.TokensDto;

import me.gijung.DMforU.service.token.GoogleToken;
import me.gijung.DMforU.service.token.RedisToken;
import me.gijung.DMforU.service.token.Token;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final GoogleToken googleToken;
    private final RedisToken redisToken;

    public void refreshToken(String token) {
        redisToken.refreshToken(token);
    }

    public void createToken(TokensDto tokensDto) {
        redisToken.createToken(tokensDto);
        googleToken.createToken(tokensDto);
    }

    public void updateToken(TokensDto tokensDto) throws FirebaseMessagingException, ExecutionException, InterruptedException {
        redisToken.updateToken(tokensDto);
        googleToken.updateToken(tokensDto);
    }

    public void deleteToken(String token) {
        redisToken.deleteToken(token);
        googleToken.deleteToken(token);
    }
}
