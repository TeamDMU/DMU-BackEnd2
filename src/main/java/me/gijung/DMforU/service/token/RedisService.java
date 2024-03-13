package me.gijung.DMforU.service.token;

import lombok.AllArgsConstructor;

import me.gijung.DMforU.model.dto.TokensDto;
import me.gijung.DMforU.service.redis.RedisToken;

import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class RedisService {

    private final RedisToken redisToken;

    public void update_Token(TokensDto tokensDto) {

        redisToken.update_Token(tokensDto);

    }

    public void delete_Token(TokensDto tokensDto) {

        redisToken.delete_Token(tokensDto);

    }
}
