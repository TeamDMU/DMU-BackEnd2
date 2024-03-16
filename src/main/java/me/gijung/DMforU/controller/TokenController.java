package me.gijung.DMforU.controller;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import me.gijung.DMforU.model.dto.TokensDto;
import me.gijung.DMforU.model.entity.Notice;
import me.gijung.DMforU.repository.NoticeRepository;
import me.gijung.DMforU.service.MessageService;
import me.gijung.DMforU.service.TokenService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/token/v1/dmu")
public class TokenController {

    private final TokenService tokenService;

    private final NoticeRepository noticeRepository;
    private final ApplicationEventPublisher eventPublisher;
    private final MessageService messageService;

    @PostMapping("/test")
    @Transactional
    public void test(@RequestBody Notice notice) {
        noticeRepository.save(notice);
        eventPublisher.publishEvent(notice);
    }

    @PostMapping("/test2")
    public void test2(@RequestBody Notice notice) throws FirebaseMessagingException {
        messageService.send_message(notice);
    }

    //redisService.updateToken = Token 유효시간 갱신 및 Token Redis Server에 등록
    //messageService.updateToken = Google FCM token & Topic 등록
    @PostMapping("/update_topic")
    public void update_topic(@RequestBody TokensDto tokensDto) {
        tokenService.update_token(tokensDto);
    }


    //Topic 구독 취소, 추후에 무결성 검증 로직 추가
    @PostMapping("/delete_topic")
    public void delete_topic(@RequestBody TokensDto tokensDto) {
        tokenService.delete_token(tokensDto);
    }
}
