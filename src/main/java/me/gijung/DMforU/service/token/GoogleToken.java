package me.gijung.DMforU.service.token;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import lombok.RequiredArgsConstructor;
import me.gijung.DMforU.config.Topic;
import me.gijung.DMforU.model.dto.TokensDto;
import me.gijung.DMforU.service.token.TokenService;
import org.springframework.stereotype.Service;

import java.util.EnumSet;
import java.util.List;
import java.util.function.BiConsumer;

@Service
@RequiredArgsConstructor
public class GoogleToken implements TokenService<TokensDto> {


    //Google FCM서버에 기기별 구독 업데이트
    public void update_Token(TokensDto tokensDto){
        firebase_Send_Token(tokensDto, (tokens, topic) -> {
            try {
                FirebaseMessaging.getInstance().subscribeToTopic(tokens, String.valueOf(topic));
            } catch (FirebaseMessagingException e) {
                throw new RuntimeException(e);
            }
        });
    }

    //Google FCM서버에 기기별 구독 삭제
    public void delete_Token(TokensDto tokensDto){
        firebase_Send_Token(tokensDto, (tokens, topic) -> {
            try {
                FirebaseMessaging.getInstance().unsubscribeFromTopic(tokens, String.valueOf(topic));
            } catch (FirebaseMessagingException e) {
                throw new RuntimeException(e);
            }
        });
    }

    //
    private void firebase_Send_Token(TokensDto tokensDto, BiConsumer<List<String>, Topic> tokenProcesss) {
        EnumSet<Topic> topics = EnumSet.allOf(Topic.class);
        List<Topic> topic1 = tokensDto.getTopic();
        for (Topic topic : topics) {
            if (topic1.contains(topic)) {
                tokenProcesss.accept(tokensDto.getTokens(), topic);
            }
        }
    }

    //모든 카테고리 삭제
    @Deprecated
    public void AllDeleteTopic(TokensDto tokensDto) throws FirebaseMessagingException {
        FirebaseMessaging instance = FirebaseMessaging
                .getInstance();
        EnumSet<Topic> topics = EnumSet.allOf(Topic.class);
        for (Topic topic : topics) {
            instance.unsubscribeFromTopic(tokensDto.getTokens(), String.valueOf(topic));
        }
    }
}
