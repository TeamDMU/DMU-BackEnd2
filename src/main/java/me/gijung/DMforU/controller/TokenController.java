package me.gijung.DMforU.controller;

import com.google.firebase.messaging.FirebaseMessagingException;
import lombok.AllArgsConstructor;
import me.gijung.DMforU.model.dto.NoticeDto;
import me.gijung.DMforU.model.dto.TokensDto;
import me.gijung.DMforU.model.entity.Notice;
import me.gijung.DMforU.repository.NoticeRepository;
import me.gijung.DMforU.service.GoogleService;
import me.gijung.DMforU.service.RedisService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/token/v1/dmu")
public class TokenController {
    private final GoogleService googleService;
    private final RedisService redisService;


    private final NoticeRepository noticeRepository;
    private final ApplicationEventPublisher eventPublisher;

    @PostMapping("/db/test")
    @Transactional
    public void tset2(@RequestBody NoticeDto noticeDto) {
        Notice notice = Notice.builder()
                .title(noticeDto.getTitle())
                .url(noticeDto.getUrl())
                .type(noticeDto.getType())
                .author(noticeDto.getAuthor())
                .date(noticeDto.getDate())
                .build();

        noticeRepository.save(notice);
        eventPublisher.publishEvent(notice);
    }
//******************************************************************8
    /**
     * 사용자 개인 설정 Topic 구독 API
     * @RequestBody
     *     private List<String> tokens;
     *     private List<Topic> topic;
     */
    //Topic 구독, 추후에 무결성 검증 로직 추가
    //redisService.updateToken = Token 유효시간 갱신 및 Token Redis Server에 등록
    //messageService.updateToken = Google FCM token & Topic 등록
    @PostMapping("/update_topic")
    public void update_topic(@RequestBody TokensDto tokensDto) throws FirebaseMessagingException {
        redisService.updateToken(tokensDto);
        googleService.updateToken(tokensDto);
    }
    /**
     * 사용자 개인 설정 Topic 삭제 API
     * @RequestBody
     *     private List<String> tokens;
     *     private List<Topic> topic;
     */
    //Topic 구독 취소, 추후에 무결성 검증 로직 추가
    @PostMapping("/delete_topic")
    public void delete_topic(@RequestBody TokensDto tokensDto) throws FirebaseMessagingException {
        googleService.deleteToken(tokensDto);
        redisService.deleteToken(tokensDto);
    }

}
