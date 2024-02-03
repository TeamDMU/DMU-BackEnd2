package me.gijung.DMforU.service;

import com.google.firebase.messaging.*;
import lombok.RequiredArgsConstructor;
import me.gijung.DMforU.model.dto.TokensDto;
import me.gijung.DMforU.service.Mesaage.FirebaseMessagingService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GoogleTokenService implements TokenService<TokensDto> {

    private final FirebaseMessagingService<FirebaseMessaging> firebaseMessaging;

    public void send_message(String topic, String title, String body) throws FirebaseMessagingException {
        Message message = firebaseMessaging.sendMessage(topic, title, body);
        FirebaseMessaging.getInstance().send(message);
    }


    //Google FCM서버에 기기별 구독 업데이트
    public void updateToken(TokensDto tokensDto) throws FirebaseMessagingException {
        TopicManagementResponse topicManagementResponse = firebaseMessaging
                .getInstance()
                .subscribeToTopic(tokensDto.getTokens(), String.valueOf(tokensDto.getTopic()));
        System.out.println("업데이트 성공갯수 :: " + topicManagementResponse.getSuccessCount());

        // 오류발생시 오류 체크
        if (topicManagementResponse.getFailureCount() != 0) {
            System.out.println("Errors  :: " + topicManagementResponse.getErrors());
        }
    }


    //Google FCM서버에 기기별 구독 삭제
    public void deleteToken(TokensDto tokensDto) throws FirebaseMessagingException {
        TopicManagementResponse topicManagementResponse = firebaseMessaging
                .getInstance()
                .unsubscribeFromTopic(tokensDto.getTokens(), String.valueOf(tokensDto.getTopic()));
        System.out.println("삭제 성공갯수 :: " + topicManagementResponse.getSuccessCount());

        // 오류발생시 오류 체크
        if (topicManagementResponse.getFailureCount() != 0) {
            System.out.println("Errors  :: " + topicManagementResponse.getErrors());
        }
    }

}
