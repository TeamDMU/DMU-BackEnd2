package me.gijung.DMforU.service;

import lombok.AllArgsConstructor;
import me.gijung.DMforU.config.Topic;
import me.gijung.DMforU.model.dto.TokensDto;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.EnumSet;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@AllArgsConstructor
public class RedisTokenService implements TokenService<TokensDto> {


    private RedisTemplate<String, String> redisTemplate;


    /**
     * Redis Server Token 유효 시간 갱신 및 등록
     * Set
     * Key - Token
     * Value - List<Topic>
     */
    public void updateToken(TokensDto tokensDto) {
        for (String token : tokensDto.getTokens()) {
            EnumSet<Topic> topics = EnumSet.allOf(Topic.class);
            List<Topic> topic1 = tokensDto.getTopic();
            for (Topic topic : topics) {
                if (topic1.contains(topic)) {
                    redisTemplate.opsForSet().add(token, String.valueOf(topic));
                    System.out.println("Redis Save Token :::" + token);
                    redisTemplate.expire(token, 30, TimeUnit.SECONDS);
                }
            }
        }
    }

    //Redis Server Token 삭제
    public void deleteToken(TokensDto tokensDto) {
        for (String token : tokensDto.getTokens()) {
            EnumSet<Topic> topics = EnumSet.allOf(Topic.class);
            List<Topic> topic1 = tokensDto.getTopic();
            for (Topic topic : topics) {
                if (topic1.contains(topic)) {
                    redisTemplate.opsForSet().remove(token, String.valueOf(topic));
                }
            }
        }
    }
}
