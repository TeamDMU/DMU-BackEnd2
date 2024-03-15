package me.gijung.DMforU.service;

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

    public void update_token(TokensDto tokensDto) {


        redisToken.update_Token(tokensDto);
        googleToken.update_Token(tokensDto);

    }

    public void delete_token(TokensDto tokensDto) {
        redisToken.delete_Token(tokensDto);
        googleToken.delete_Token(tokensDto);
    }
}
