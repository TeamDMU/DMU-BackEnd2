package me.gijung.DMforU.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import me.gijung.DMforU.model.dto.TokensDto;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RedisService implements TokenService<TokensDto> {

    private RedisTemplate<String, String> redisTemplate;
    //Redis 기기별 토큰 타임스탬프 업데이트
    public void updateToken(TokensDto tokensDto) {
        try {
            for (String token : tokensDto.getTokens()) {
                redisTemplate.opsForList().rightPush(token, String.valueOf(LocalDateTime.now()));
            }
        } catch (Exception e) {
            System.out.println("Redis_Update_Error = " + e);
        }

    }

    //Redis 기기별 토큰 삭제
    public void deleteToken(TokensDto tokensDto) {
        for (String token : tokensDto.getTokens()) {
            System.out.println("token = " + token);
            try {
                redisTemplate.delete(token);
            }catch (Exception e){
                System.out.println("Redis_Delete_Error = " + e);
            }
        }
    }

    public List<String> getData(String topic) {
        return redisTemplate.opsForList().range(topic,0,-1);
    }


}
