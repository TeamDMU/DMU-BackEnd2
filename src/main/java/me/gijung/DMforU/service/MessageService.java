package me.gijung.DMforU.service;

import com.google.firebase.messaging.*;
import lombok.RequiredArgsConstructor;
import me.gijung.DMforU.service.Mesaage.FirebaseMessagingService;
import me.gijung.DMforU.service.Mesaage.Messaging;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService{

    private final FirebaseMessagingService<FirebaseMessaging> firebaseMessaging;

    public void send_message(String topic, String title, String body) throws FirebaseMessagingException {
        Message message = firebaseMessaging.sendMessage(topic, title, body);
        String send = FirebaseMessaging.getInstance().send(message);
        System.out.println("send = " + send);

    }

    public void update_topic(List<String> tokens, String topic) throws FirebaseMessagingException {
        TopicManagementResponse topicManagementResponse = firebaseMessaging.getInstance().subscribeToTopic(tokens, topic);
        System.out.println("업데이트 성공갯수 :: " + topicManagementResponse.getSuccessCount());
        System.out.println("업데이트 실패갯수 :: " + topicManagementResponse.getFailureCount());
    }
    public void delete_topic(List<String> tokens, String topic) throws FirebaseMessagingException {
        TopicManagementResponse topicManagementResponse = firebaseMessaging.getInstance().unsubscribeFromTopic(tokens, topic);
        System.out.println("삭제 성공갯수 :: " + topicManagementResponse.getSuccessCount());
        System.out.println("삭제 실패갯수 :: " + topicManagementResponse.getFailureCount());

    }
}
