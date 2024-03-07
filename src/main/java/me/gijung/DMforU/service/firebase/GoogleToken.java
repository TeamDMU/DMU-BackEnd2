package me.gijung.DMforU.service.firebase;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import lombok.RequiredArgsConstructor;
import me.gijung.DMforU.config.Topic;
import me.gijung.DMforU.model.dto.TokensDto;
import me.gijung.DMforU.service.Mesaage.FirebaseMessagingService;
import org.springframework.stereotype.Service;

import java.util.EnumSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GoogleToken{
    private final FirebaseMessagingService<FirebaseMessaging> firebaseMessaging;

    /**
     * FCM Topic 기기별 구독 메서드
     * 여러대가 동시에 여러개의 토픽을 구독한다는 가정
     * Token - List<Token>
     * Topic - List<Topic>
     */
    //Google FCM서버에 기기별 구독 업데이트
    public void updateToken(TokensDto tokensDto) throws FirebaseMessagingException {
        FirebaseMessaging instance = firebaseMessaging
                .getInstance();
        EnumSet<Topic> topics = EnumSet.allOf(Topic.class);
        List<Topic> topic1 = tokensDto.getTopic();
        for (Topic topic : topics) {
            if (topic1.contains(topic)) {
                instance.subscribeToTopic(tokensDto.getTokens(), String.valueOf(topic));
            }
        }
    }

    /**
     * FCM Topic 기기별 삭제 메서드
     * 여러대가 동시에 여러개의 토픽을 삭제한다는 가정
     * Token - List<Token>
     * Topic - List<Topic>
     */
    //Google FCM서버에 기기별 구독 삭제
    public void deleteToken(TokensDto tokensDto) throws FirebaseMessagingException {
        FirebaseMessaging instance = firebaseMessaging
                .getInstance();
        EnumSet<Topic> topics = EnumSet.allOf(Topic.class);
        List<Topic> topic1 = tokensDto.getTopic();
        for (Topic topic : topics) {
            if (topic1.contains(topic)) {
                instance.unsubscribeFromTopic(tokensDto.getTokens(), String.valueOf(topic));
            }
        }
    }

    /**
     * FCM Topic 기기별  모두 삭제 메서드 ( 유효기간 만료에 사용 )
     * Token - List<Token>
     * Topic - List<Topic>
     */
    //모든 카테고리 삭제
    public void AllDeleteTopic(TokensDto tokensDto) throws FirebaseMessagingException {
        FirebaseMessaging instance = firebaseMessaging
                .getInstance();
        EnumSet<Topic> topics = EnumSet.allOf(Topic.class);
        for (Topic topic : topics) {
            instance.unsubscribeFromTopic(tokensDto.getTokens(), String.valueOf(topic));
        }
    }}
