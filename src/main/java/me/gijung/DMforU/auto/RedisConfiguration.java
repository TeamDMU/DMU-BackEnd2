package me.gijung.DMforU.auto;

import com.google.firebase.messaging.FirebaseMessagingException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import me.gijung.DMforU.model.dto.TokensDto;
import me.gijung.DMforU.service.GoogleTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.Collections;

@Configuration
@AllArgsConstructor
public class RedisConfiguration {

    private final GoogleTokenService googleTokenService;

    //Token 만료시 자동으로 Google FCM Server에 Token삭제 요청 전송
    @Bean
    RedisMessageListenerContainer redisMessageListenerContainer(RedisConnectionFactory connectionFactory) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message, byte[] pattern) {
                String expiredKey = new String(message.getBody());
                TokensDto tokensDto = new TokensDto();
                tokensDto.setTokens(Collections.singletonList(expiredKey));
                try {
                    googleTokenService.deleteToken(tokensDto);
                } catch (FirebaseMessagingException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("만료된 Token :: " + expiredKey);
                // 만료된 key에 대한 추가 로직을 여기에 구현합니다.
            }
        }, new PatternTopic("__keyevent@*__:expired"));
        return container;
    }


    //RedisTemplate 재정의
    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericToStringSerializer<>(String.class));
        return redisTemplate;
    }
}
