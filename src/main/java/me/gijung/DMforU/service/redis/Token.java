package me.gijung.DMforU.service.redis;

import lombok.RequiredArgsConstructor;
import me.gijung.DMforU.config.Topic;
import me.gijung.DMforU.model.dto.DepartmentDto;
import me.gijung.DMforU.model.dto.TokensDto;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.EnumSet;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;

@Service
@RequiredArgsConstructor
public class Token {
    private final RedisTemplate<String, String> redisTemplate;
    /**
     * Redis Server Token 유효 시간 갱신 및 등록
     * Set
     * Key - Token
     * Value - List<Topic>
     */

    public void updateToken(TokensDto tokensDto) {

        long currentTimeMillis = System.currentTimeMillis();
        double score = currentTimeMillis + TimeUnit.SECONDS.toMillis(100);


        processToken(tokensDto, (token, topic) -> {
            redisTemplate.opsForZSet().add(token, String.valueOf(topic), score);
            redisTemplate.expire(token, 100, TimeUnit.SECONDS);
        });
    }

    //Redis Server Token 삭제
    public void deleteToken(TokensDto tokensDto) {
        processToken(tokensDto, (token, topic) -> {
            redisTemplate.opsForZSet().remove(token, String.valueOf(topic));
        });
    }
    private void processToken(TokensDto tokensDto, BiConsumer<String, Topic> tokenProcessor) {
        for (String token : tokensDto.getTokens()) {
            EnumSet<Topic> topics = EnumSet.allOf(Topic.class);
            List<Topic> topic1 = tokensDto.getTopic();
            for (Topic topic : topics) {
                if (topic1.contains(topic)) {
                    tokenProcessor.accept(token, topic);
                }
            }
        }
    }


}
