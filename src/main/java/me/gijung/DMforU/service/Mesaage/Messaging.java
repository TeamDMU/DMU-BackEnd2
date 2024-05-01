package me.gijung.DMforU.service.Mesaage;

import com.google.firebase.messaging.*;
import lombok.RequiredArgsConstructor;
import me.gijung.DMforU.model.dto.MessageDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class Messaging{


    //대학공지
    public static Message buildMessage(MessageDto messageDto) {
        return Message.builder()
                .setTopic(messageDto.getTopic())
                .setNotification(Notification.builder()
                        .setTitle(messageDto.getTitle())
                        .setBody(messageDto.getBody())
                        .build())
                .putData("url", messageDto.getUrl())
                .putData("title", messageDto.getTitle())
                .putData("body", messageDto.getBody())
                .build();
    }


    //학과공지
    public static MulticastMessage buildMessage(MessageDto messageDto, List<String> tokenList) {
        return MulticastMessage.builder()
                .setNotification(Notification.builder()
                        .setTitle(messageDto.getTitle())
                        .setBody(messageDto.getBody())
                        .build())
                .addAllTokens(tokenList)
                .putData("url", messageDto.getUrl())
                .putData("title", messageDto.getTitle())
                .putData("body", messageDto.getBody())
                .build();
    }
}
