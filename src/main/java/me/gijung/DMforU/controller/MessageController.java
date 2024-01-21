package me.gijung.DMforU.controller;

import com.google.firebase.messaging.FirebaseMessagingException;
import lombok.AllArgsConstructor;
import me.gijung.DMforU.model.dto.MessageDto;
import me.gijung.DMforU.model.dto.TokensDto;
import me.gijung.DMforU.service.MessageService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/message")
public class MessageController {
    private final MessageService messageService;
    @PostMapping("/send")
    public void send_message(@RequestBody MessageDto messageDto) throws FirebaseMessagingException {
        messageService.send_message(messageDto.getTopic(), messageDto.getTitle(), messageDto.getBody());
    }
    @PostMapping("/update_topic")
    public void update_topic(@RequestBody TokensDto tokensDto) throws FirebaseMessagingException {
        messageService.update_topic(tokensDto.getTokens(), tokensDto.getTopic());
    }
    @PostMapping("/delete_topic")
    public void delete_topic(@RequestBody TokensDto tokensDto) throws FirebaseMessagingException {
        messageService.delete_topic(tokensDto.getTokens(), tokensDto.getTopic());
    }

}
