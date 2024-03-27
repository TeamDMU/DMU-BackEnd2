package me.gijung.DMforU.service;

import com.google.firebase.messaging.FirebaseMessagingException;
import lombok.RequiredArgsConstructor;
import me.gijung.DMforU.model.dto.TokensDto;

import me.gijung.DMforU.service.token.Token;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final Token<TokensDto> googleToken;
    private final Token<TokensDto> redisToken;

    public void refreshToken(TokensDto tokensDto) {
        redisToken.refreshToken(tokensDto);
    }

    public void createToken(TokensDto tokensDto) {
        redisToken.createToken(tokensDto);
        googleToken.createToken(tokensDto);
    }

    public void updateToken(TokensDto tokensDto) throws FirebaseMessagingException, ExecutionException, InterruptedException {
        redisToken.updateToken(tokensDto);
        googleToken.updateToken(tokensDto);
    }

    public void deleteToken(TokensDto tokensDto) {
        redisToken.deleteToken(tokensDto);
        googleToken.deleteToken(tokensDto);
    }
}
