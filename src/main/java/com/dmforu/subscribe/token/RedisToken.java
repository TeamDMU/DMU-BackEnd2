package com.dmforu.subscribe.token;

import com.dmforu.subscribe.config.Topic;
import com.dmforu.subscribe.dto.TokensDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;

@Service
@RequiredArgsConstructor
@Qualifier("redisToken")
public class RedisToken implements Token<TokensDto> {

    private final RedisTemplate<String, String> redisTemplate;

    public void createToken(TokensDto tokensDto) {
        processToken(tokensDto, (token, topic) -> {
            redisTemplate.opsForZSet().add(token, String.valueOf(topic), -1);
            redisTemplate.expire(token,30, TimeUnit.DAYS);
        });
    }

    public void refreshToken(TokensDto tokensDto) {
        redisTemplate.expire(tokensDto.getToken(),30,TimeUnit.DAYS);
    }


    //Redis Server Token Update
    public void updateToken(TokensDto tokensDto) {
        redisTemplate.opsForZSet().removeRange(tokensDto.getToken(), 1, -1);
        processToken(tokensDto, (token, topic) -> {
            redisTemplate.opsForZSet().add(token, String.valueOf(topic), -1);
        });
    }

    //Redis Server Token Delete
    public void deleteToken(TokensDto tokensDto) {
        processToken(tokensDto, (token, topic) -> {
            redisTemplate.opsForZSet().remove(token, String.valueOf(topic));
        });
    }

    private void processToken(TokensDto tokensDto, BiConsumer<String, Topic> tokenProcessor) {
        List<Topic> topic1 = tokensDto.getTopic();
        for (Topic topic : Topic.values()) {
            if (topic1.contains(topic)) {
                tokenProcessor.accept(tokensDto.getToken(), topic);
            }
        }
    }
}
