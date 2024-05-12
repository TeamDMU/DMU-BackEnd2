package me.gijung.DMforU.controller;

import com.google.firebase.messaging.FirebaseMessagingException;
import lombok.AllArgsConstructor;
import me.gijung.DMforU.model.dto.TokenRequestDto;
import me.gijung.DMforU.model.dto.TokensDto;
import me.gijung.DMforU.model.dto.TopicRequestDto;
import me.gijung.DMforU.service.TokenService;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;

@RestController
@AllArgsConstructor
@RequestMapping("/token/v1/dmu")
public class TokenController {

    private final TokenService tokenService;


    //기존 Token TTL Refresh
    @PostMapping("/refreshToken")
    public void refreshTopic(@RequestBody TokenRequestDto tokenRequestDto){
        tokenService.refreshToken(tokenRequestDto.getToken());
    }

    //신규 Token 생성 URL
    @PostMapping("/createTopic")
    public void createTopic(@RequestBody TopicRequestDto tokenDto){
        tokenService.createToken(
                new TokensDto(Arrays.asList(tokenDto.getToken()), tokenDto.getTopics())
        );
    }

    //redisService.updateToken = Token 유효시간 갱신 및 Token Redis Server에 등록
    //messageService.updateToken = Google FCM token & Topic 등록
    @PostMapping("/updateTopic")
    public void updateTopic(@RequestBody TopicRequestDto tokenDto) throws ExecutionException, FirebaseMessagingException, InterruptedException {
        tokenService.updateToken(
                new TokensDto(Arrays.asList(tokenDto.getToken()), tokenDto.getTopics())
        );
    }

    //Topic 구독 취소, 추후에 무결성 검증 로직 추가

    @PostMapping("/deleteTopic")
    public void deleteTopic(@RequestBody TokenRequestDto tokenRequestDto) {
        tokenService.deleteToken(tokenRequestDto.getToken());
    }
}
