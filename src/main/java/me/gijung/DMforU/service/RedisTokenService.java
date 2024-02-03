package me.gijung.DMforU.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import me.gijung.DMforU.model.dto.TokensDto;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RedisTokenService implements TokenService<TokensDto> {

    private RedisTemplate<String, String> redisTemplate;
    //Redis 기기별 토큰 타임스탬프 업데이트
    //Token 최초 등록
    public void save(TokensDto tokensDto) {
        try {
            for (String token : tokensDto.getTokens()) {
                redisTemplate.opsForValue().set(token, String.valueOf(LocalDateTime.now()), 10, TimeUnit.SECONDS);       }
        } catch (Exception e) {
            System.out.println("Redis_Update_Error = " + e);
        }
    }

    //Token 유효시간 갱신
    public void updateToken(TokensDto tokensDto) {
        for (String token : tokensDto.getTokens()) {
            redisTemplate.expire(token, 10, TimeUnit.SECONDS);
        }
    }

    //사용자가 직접
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
