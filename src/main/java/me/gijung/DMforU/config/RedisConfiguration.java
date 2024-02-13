package me.gijung.DMforU.config;

import com.google.firebase.messaging.FirebaseMessagingException;
import lombok.AllArgsConstructor;
import me.gijung.DMforU.model.dto.TokensDto;
import me.gijung.DMforU.service.GoogleTokenService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
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
                TokensDto tokensDto = TokensDto
                        .builder()
                        .tokens(Collections.singletonList(expiredKey))
                        .build();
                try {
                    googleTokenService.AlldeleteTopic(tokensDto);
                } catch (FirebaseMessagingException e) {
                    throw new RuntimeException(e);
                }
            }
        }, new PatternTopic("__keyevent@*__:expired"));
        return container;
    }

    // RedisTemplate 빈 설정
    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer()); // 더 적합한 직렬화 방식 선택 가능
        return redisTemplate;
    }
}
