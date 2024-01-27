package me.gijung.DMforU.controller;

import com.google.firebase.messaging.FirebaseMessagingException;
import lombok.AllArgsConstructor;
import me.gijung.DMforU.model.dto.MessageDto;
import me.gijung.DMforU.model.dto.TokensDto;
import me.gijung.DMforU.service.MessageService;
import me.gijung.DMforU.service.RedisService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/message")
public class MessageController {
    private final MessageService messageService;
    private final RedisService redisService;

    //추후에 자동화 시킬 예정
    @PostMapping("/send")
    public void send_message(@RequestBody MessageDto messageDto) throws FirebaseMessagingException {
        messageService.send_message(messageDto.getTopic(), messageDto.getTitle(), messageDto.getBody());
    }

    //사용자 구독, 추후에 무결성 검증 로직 추가
    @PostMapping("/update_topic")
    public void update_topic(@RequestBody TokensDto tokensDto) throws FirebaseMessagingException {
        try {
            redisService.updateToken(String.valueOf(tokensDto.getTopic()), tokensDto.getTokens());
            messageService.updateToken(String.valueOf(tokensDto.getTopic()),tokensDto.getTokens());
        }catch (Exception e){
            System.out.println("Error = " + e);
        }
    }

    //사용자 구독 취소, 추후에 무결성 검증 로직 추가
    @PostMapping("/delete_topic")
    public void delete_topic(@RequestBody TokensDto tokensDto) throws FirebaseMessagingException {

        try {
            redisService.deleteToken(String.valueOf(tokensDto.getTopic()), tokensDto.getTokens());
            messageService.deleteToken(String.valueOf(tokensDto.getTopic()),tokensDto.getTokens());
        }catch (Exception e){
            System.out.println("Error = " + e);
        }


    }

}
