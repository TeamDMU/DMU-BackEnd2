package me.gijung.DMforU.service.token;

import lombok.RequiredArgsConstructor;
import me.gijung.DMforU.config.Topic;
import me.gijung.DMforU.model.dto.TokensDto;
import me.gijung.DMforU.service.token.TokenService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;

@Service
@RequiredArgsConstructor
public class RedisToken implements TokenService<TokensDto> {

    private final RedisTemplate<String, String> redisTemplate;

    //Redis Server Token Update
    public void updateToken(TokensDto tokensDto) {
        for (String tokens : tokensDto.getTokens()) {
            redisTemplate.opsForZSet().removeRange(tokens, 1, -1);
            processToken(tokensDto, (token, topic) -> {
                redisTemplate.opsForZSet().add(token, String.valueOf(topic), -1);
            });
        }
    }

    //Redis Server Token Delete
    public void deleteToken(TokensDto tokensDto) {
        processToken(tokensDto, (token, topic) -> {
            redisTemplate.opsForZSet().remove(token, String.valueOf(topic));
        });
    }

    private void processToken(TokensDto tokensDto, BiConsumer<String, Topic> tokenProcessor) {
        for (String token : tokensDto.getTokens()) {
            List<Topic> topic1 = tokensDto.getTopic();
            for (Topic topic : Topic.values()) {
                if (topic1.contains(topic)) {
                    tokenProcessor.accept(token, topic);
                }
            }
        }
    }
}
