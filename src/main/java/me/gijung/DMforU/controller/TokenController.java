package me.gijung.DMforU.controller;

import com.google.firebase.messaging.FirebaseMessagingException;
import lombok.AllArgsConstructor;
import me.gijung.DMforU.model.dto.RequestTokenDto;
import me.gijung.DMforU.service.TokenService;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@AllArgsConstructor
@RequestMapping("/token/v1/dmu")
public class TokenController {

    private final TokenService tokenService;

    //기존 Token TTL Refresh
    @PostMapping("/refreshToken")
    public void refreshTopic(@RequestBody RequestTokenDto tokensDto){
        tokenService.refreshToken(tokensDto);
    }

    //redisService.updateToken = Token 유효시간 갱신 및 Token Redis Server에 등록
    //messageService.updateToken = Google FCM token & Topic 등록
    @PostMapping("/updateTopic")
    public void updateTopic(@RequestBody RequestTokenDto tokensDto) throws FirebaseMessagingException, ExecutionException, InterruptedException {
        tokenService.updateTopic(tokensDto);
    }

    //Topic 구독 취소, 추후에 무결성 검증 로직 추가
    @PostMapping("/deleteTopic")
    public void deleteTopic(@RequestBody RequestTokenDto tokensDto) {
        tokenService.deleteTopic(tokensDto);
    }
}
