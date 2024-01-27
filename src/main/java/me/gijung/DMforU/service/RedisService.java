package me.gijung.DMforU.service;

import com.google.firebase.messaging.FirebaseMessagingException;
import lombok.AllArgsConstructor;
import me.gijung.DMforU.model.dto.TokensDto;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RedisService implements TokenService<TokensDto> {

    private RedisTemplate<String, String> redisTemplate;

    public void updateToken(String topic, List<String> Tokens) {
        redisTemplate.opsForList().rightPushAll(topic,Tokens);
//        redisTemplate.expire(topic, 60, TimeUnit.SECONDS);
    }

    public void deleteToken(String topic, List<String> Tokens) throws FirebaseMessagingException {
        for (String token : Tokens) {
            redisTemplate.opsForList().remove(topic, 1, token);
        }
    }

    public List<String> getData(String topic) {
        return redisTemplate.opsForList().range(topic,0,-1);
    }


}
