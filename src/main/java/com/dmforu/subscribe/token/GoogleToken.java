package com.dmforu.subscribe.token;

import com.dmforu.subscribe.config.Topic;
import com.dmforu.subscribe.dto.TokensDto;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.function.BiConsumer;

@Service
@Qualifier("googleToken")
@RequiredArgsConstructor
public class GoogleToken {


    //Google FCM서버에 기기별 구독 업데이트
    public void createToken(TokensDto tokensDto) {
        firebaseSendToken(tokensDto, (token, topic) -> {
            try {
                FirebaseMessaging.getInstance().subscribeToTopic(token, String.valueOf(topic));
            } catch (FirebaseMessagingException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void updateToken(TokensDto tokensDto) throws FirebaseMessagingException {
        deleteAllTopic(tokensDto);
        firebaseSendToken(tokensDto, (token, topic) -> {
            try {
                FirebaseMessaging.getInstance().subscribeToTopic(token, String.valueOf(topic));
            } catch (FirebaseMessagingException e) {
                throw new RuntimeException(e);
            }
        });
    }

    //Google FCM서버에 기기별 구독 삭제
    public void deleteToken(String token){
        EnumSet<Topic> topics = EnumSet.allOf(Topic.class);
        for (Topic topic : topics) {
            try {
                FirebaseMessaging.getInstance().unsubscribeFromTopic(Arrays.asList(token), String.valueOf(topic));
            } catch (FirebaseMessagingException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void firebaseSendToken(TokensDto tokensDto, BiConsumer<List<String>, Topic> tokenProcesss) {
        EnumSet<Topic> topics = EnumSet.allOf(Topic.class);
        List<Topic> topic1 = tokensDto.getTopic();
        for (Topic topic : topics) {
            if (topic1.contains(topic)) {
                tokenProcesss.accept(Collections.singletonList(tokensDto.getToken()), topic);
            }
        }
    }

    private void deleteAllTopic(TokensDto tokensDto) throws FirebaseMessagingException {
        for (Topic value : Topic.values()) {
            FirebaseMessaging.getInstance().unsubscribeFromTopic(Collections.singletonList(tokensDto.getToken()), String.valueOf(value));
        }
    }
}
