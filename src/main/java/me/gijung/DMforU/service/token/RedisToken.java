package me.gijung.DMforU.service.token;

import lombok.RequiredArgsConstructor;
import me.gijung.DMforU.config.Topic;
import me.gijung.DMforU.model.dto.ServiceTokensDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;

@Service
@RequiredArgsConstructor
@Qualifier("redisToken")
public class RedisToken implements Token<ServiceTokensDto> {

    private final RedisTemplate<String, String> redisTemplate;

    public void createToken(ServiceTokensDto tokensDto) {
        processToken(tokensDto, (token, topic) -> {
            redisTemplate.opsForZSet().add(token, String.valueOf(topic), -1);
            redisTemplate.expire(token,30,TimeUnit.DAYS);
        });
    }

    public void refreshToken(ServiceTokensDto tokensDto) {
            redisTemplate.expire(tokensDto.getTokens(),30,TimeUnit.DAYS);
    }


    //Redis Server Token Update
    public void updateToken(ServiceTokensDto tokensDto) {
            redisTemplate.opsForZSet().removeRange(tokensDto.getTokens(), 1, -1);
            processToken(tokensDto, (token, topic) -> {
                redisTemplate.opsForZSet().add(token, String.valueOf(topic), -1);
            });
    }

    //Redis Server Token Delete
    public void deleteToken(ServiceTokensDto tokensDto) {
        processToken(tokensDto, (token, topic) -> {
            redisTemplate.opsForZSet().remove(token, String.valueOf(topic));
        });
    }

    private void processToken(ServiceTokensDto tokensDto, BiConsumer<String, Topic> tokenProcessor) {
            List<Topic> topic1 = tokensDto.getTopic();
            for (Topic topic : Topic.values()) {
                if (topic1.contains(topic)) {
                    tokenProcessor.accept(tokensDto.getTokens(), topic);
                }
        }
    }
}
