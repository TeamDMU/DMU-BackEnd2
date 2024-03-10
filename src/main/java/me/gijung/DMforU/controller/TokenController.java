package me.gijung.DMforU.controller;

import lombok.AllArgsConstructor;
import me.gijung.DMforU.model.dto.TokensDto;
import me.gijung.DMforU.service.GoogleService;
import me.gijung.DMforU.service.RedisService;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/token/v1/dmu")
public class TokenController {

    private final GoogleService googleService;
    private final RedisService redisService;

    //Topic 구독, 추후에 무결성 검증 로직 추가
    //redisService.updateToken = Token 유효시간 갱신 및 Token Redis Server에 등록
    //messageService.updateToken = Google FCM token & Topic 등록
    @PostMapping("/update_topic")
    public void update_topic(@RequestBody TokensDto tokensDto){

        redisService.updateToken(tokensDto);
        googleService.updateToken(tokensDto);

    }

    //Topic 구독 취소, 추후에 무결성 검증 로직 추가
    @PostMapping("/delete_topic")
    public void delete_topic(@RequestBody TokensDto tokensDto){

        googleService.deleteToken(tokensDto);
        redisService.deleteToken(tokensDto);

    }

}
