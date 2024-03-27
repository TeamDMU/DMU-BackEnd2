package me.gijung.DMforU.controller;

import com.google.firebase.messaging.FirebaseMessagingException;
import lombok.AllArgsConstructor;
import me.gijung.DMforU.model.dto.TokensDto;
import me.gijung.DMforU.service.TokenService;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@AllArgsConstructor
@RequestMapping("/token/v1/dmu")
public class TokenController {

    private final TokenService tokenService;

    //기존 Token TTL Refresh
    @PostMapping("/refresh_token")
    public void refreshTopic(@RequestBody TokensDto tokensDto){
        tokenService.refreshToken(tokensDto);
    }

    //신규 Token 생성 URL
    @PostMapping("/create_topic")
    public void createTopic(@RequestBody TokensDto tokensDto){
        tokenService.createToken(tokensDto);
    }

    //redisService.updateToken = Token 유효시간 갱신 및 Token Redis Server에 등록
    //messageService.updateToken = Google FCM token & Topic 등록
    @PostMapping("/updateTopic")
    public void update_topic(@RequestBody TokensDto tokensDto) throws ExecutionException, FirebaseMessagingException, InterruptedException {
        tokenService.updateToken(tokensDto);
    }

    //Topic 구독 취소, 추후에 무결성 검증 로직 추가

    @PostMapping("/deleteTopic")
    public void delete_topic(@RequestBody TokensDto tokensDto) {
        tokenService.deleteToken(tokensDto);
    }
}
