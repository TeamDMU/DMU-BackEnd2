package me.gijung.DMforU.service;

import lombok.AllArgsConstructor;

import me.gijung.DMforU.model.dto.TokensDto;

import me.gijung.DMforU.service.redis.Token;



import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class RedisService{

    private final Token token;

    public void updateToken(TokensDto tokensDto){

        token.update_Token(tokensDto);

    }

    public void deleteToken(TokensDto tokensDto){

        token.delete_Token(tokensDto);

    }
}
