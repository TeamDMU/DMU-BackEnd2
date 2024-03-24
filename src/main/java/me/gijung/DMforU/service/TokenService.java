package me.gijung.DMforU.service;

import com.google.firebase.messaging.FirebaseMessagingException;
import lombok.RequiredArgsConstructor;
import me.gijung.DMforU.model.dto.TokensDto;
import me.gijung.DMforU.service.token.GoogleToken;
import me.gijung.DMforU.service.token.RedisToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final GoogleToken googleToken;
    private final RedisToken redisToken;

    public void updateToken(TokensDto tokensDto) {

        redisToken.updateToken(tokensDto);
        googleToken.updateToken(tokensDto);

    }

    public void deleteToken(TokensDto tokensDto) {

        redisToken.deleteToken(tokensDto);
        googleToken.deleteToken(tokensDto);

    }

    public void allDeleteToken(TokensDto tokensDto) throws FirebaseMessagingException {

        googleToken.AllDeleteTopic(tokensDto);

    }
}
