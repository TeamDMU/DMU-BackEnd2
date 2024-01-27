package me.gijung.DMforU.service;

import com.google.firebase.messaging.*;
import lombok.RequiredArgsConstructor;
import me.gijung.DMforU.model.dto.TokensDto;
import me.gijung.DMforU.service.Mesaage.FirebaseMessagingService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService implements TokenService<TokensDto> {

    private final FirebaseMessagingService<FirebaseMessaging> firebaseMessaging;

    public void send_message(String topic, String title, String body) throws FirebaseMessagingException {
        Message message = firebaseMessaging.sendMessage(topic, title, body);
        FirebaseMessaging.getInstance().send(message);
    }

    public void updateToken(String topic, List<String> Tokens) throws FirebaseMessagingException {
        TopicManagementResponse topicManagementResponse = firebaseMessaging.getInstance().subscribeToTopic(Tokens, topic);
        System.out.println("업데이트 성공갯수 :: " + topicManagementResponse.getSuccessCount());
        System.out.println("업데이트 실패갯수 :: " + topicManagementResponse.getFailureCount());
    }

    public void deleteToken(String topic, List<String> Tokens) throws FirebaseMessagingException {
        TopicManagementResponse topicManagementResponse = firebaseMessaging.getInstance().unsubscribeFromTopic(Tokens, topic);
        System.out.println("삭제 성공갯수 :: " + topicManagementResponse.getSuccessCount());
        System.out.println("삭제 실패갯수 :: " + topicManagementResponse.getFailureCount());
    }

}
