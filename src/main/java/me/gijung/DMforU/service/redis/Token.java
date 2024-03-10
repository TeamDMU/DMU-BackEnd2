package me.gijung.DMforU.service.redis;

import lombok.RequiredArgsConstructor;
import me.gijung.DMforU.config.Topic;
import me.gijung.DMforU.model.dto.TokensDto;
import me.gijung.DMforU.service.token.TokenService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.EnumSet;
import java.util.List;
import java.util.function.BiConsumer;

@Service
@RequiredArgsConstructor
public class Token implements TokenService<TokensDto> {

    private final RedisTemplate<String, String> redisTemplate;

    /**
     * Redis Server Token 업데이트
     * Set
     * Key - Token
     * Value - List<Topic>
     */
    public void update_Token(TokensDto tokensDto) {
        processToken(tokensDto, (token, topic) -> {
            redisTemplate.opsForZSet().add(token, String.valueOf(topic), 1);
        });
    }

    //Redis Server Token 삭제
    public void delete_Token(TokensDto tokensDto) {
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
