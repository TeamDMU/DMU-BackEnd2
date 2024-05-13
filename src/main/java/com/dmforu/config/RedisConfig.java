package com.dmforu.config;

import com.dmforu.subscribe.config.Topic;
import com.dmforu.subscribe.dto.TokensDto;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.Collections;
import java.util.EnumSet;

@Configuration
@RequiredArgsConstructor
public class RedisConfig {


    @Value("${spring.data.redis.host}")
    private String host;

    @Value("${spring.data.redis.port}")
    private int port;

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {

        return new LettuceConnectionFactory(host, port);
    }

    //    //Token 만료시 자동으로 Google FCM Server에 Token삭제 요청 전송
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
                        .token(expiredKey)
                        .build();
                try {
                    AllDeleteTopic(tokensDto);
                } catch (FirebaseMessagingException e) {
                    throw new RuntimeException(e);
                }
            }
        }, new PatternTopic("__keyevent@*__:expired"));
        return container;
    }

    // RedisTemplate 빈 설정
    // 직렬화 하지 않으면 cli모드에서 데이터 조회시 데이터 깨짐 현상
    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory());
        redisTemplate.setKeySerializer(new StringRedisSerializer());   // Key: String
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(String.class));  // Value: 직렬화에 사용할 Object 사용하기
        return redisTemplate;
    }

    private void AllDeleteTopic(TokensDto tokensDto) throws FirebaseMessagingException {
        FirebaseMessaging instance = FirebaseMessaging
                .getInstance();
        EnumSet<Topic> topics = EnumSet.allOf(Topic.class);
        for (Topic topic : topics) {
            instance.unsubscribeFromTopic(Collections.singletonList(tokensDto.getToken()), String.valueOf(topic));
        }
    }
}
