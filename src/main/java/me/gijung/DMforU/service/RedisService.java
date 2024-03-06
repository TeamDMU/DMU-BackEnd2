package me.gijung.DMforU.service;

import lombok.AllArgsConstructor;
import me.gijung.DMforU.config.Major;
import me.gijung.DMforU.config.Topic;
import me.gijung.DMforU.model.dto.DepartmentDto;
import me.gijung.DMforU.model.dto.TokensDto;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.EnumSet;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;

@Service
@AllArgsConstructor
public class RedisService implements TokenService<TokensDto> {


    private RedisTemplate<String, String> redisTemplate;


    /**
     * 클라이언트에서 학과 설정시 학과, 토큰을 
     * Key - Value 로 Redis Server에 저장
     */
    public void updateDepartment(DepartmentDto dto) {
        redisTemplate.opsForList().rightPushAll(dto.getDepartment(), dto.getTokens());
    }
    /**
     * Redis Server Token 유효 시간 갱신 및 등록
     * Set
     * Key - Token
     * Value - List<Topic>
     */
    public void updateToken(TokensDto tokensDto) {
        processToken(tokensDto, (token, topic) -> {
            redisTemplate.opsForSet().add(token, String.valueOf(topic));
            redisTemplate.expire(token, 30, TimeUnit.SECONDS);
        });
    }

    //Redis Server Token 삭제
    public void deleteToken(TokensDto tokensDto) {
        processToken(tokensDto, (token, topic) -> {
            redisTemplate.opsForSet().remove(token, String.valueOf(topic));
        });
    }

//    public void deleteDepartment(List<String> tokens) {
//        ListOperations<String, String> listOperations = redisTemplate.opsForList();
//        Major[] values = Major.values();
//        for (Major value : values) {
//            List<String> range = listOperations.range(value.getNoticeCode(), 0, -1);
//            for (String list : range) {
//                        redisTemplate.opsForList().remove(list, 0, tokens);
//            }
//        }
//    }

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
