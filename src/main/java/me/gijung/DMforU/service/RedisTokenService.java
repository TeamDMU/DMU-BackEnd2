package me.gijung.DMforU.service;

import lombok.AllArgsConstructor;
import me.gijung.DMforU.config.Topic;
import me.gijung.DMforU.model.dto.TokensDto;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
@AllArgsConstructor
public class RedisTokenService implements TokenService<TokensDto> {


    private RedisTemplate<String, String> redisTemplate;
    //Redis 기기별 토큰 타임스탬프 업데이트
    //Redis Server Token 최초 등록

    //Redis Server Token 유효시간 갱신
    public void updateToken(TokensDto tokensDto) {
        for (String token : tokensDto.getTokens()) {
            for(Topic topic : tokensDto.getTopic()) {
                redisTemplate.opsForSet().add(token, String.valueOf(topic));
                System.out.println("Token Save");
                redisTemplate.expire(token, 20, TimeUnit.SECONDS);
            }
        }
    }

    //Redis Server Token 삭제
    public void deleteToken(TokensDto tokensDto) {
        for (String token : tokensDto.getTokens()) {
            System.out.println("token = " + token);
                redisTemplate.delete(token);
        }
    }
}
