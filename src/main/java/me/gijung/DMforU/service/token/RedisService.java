package me.gijung.DMforU.service.token;

import lombok.AllArgsConstructor;

import me.gijung.DMforU.model.dto.TokensDto;
import me.gijung.DMforU.service.redis.Token;

import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class RedisService implements TokenService<TokensDto> {

    private final Token token;

    public void updateToken(TokensDto tokensDto) {
        token.updateToken(tokensDto);
    }

    public void deleteToken(TokensDto tokensDto) {
        token.deleteToken(tokensDto);
    }
}
