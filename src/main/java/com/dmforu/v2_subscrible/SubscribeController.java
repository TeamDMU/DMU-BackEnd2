package com.dmforu.v2_subscrible;

import com.dmforu.notice.Notice;
import com.dmforu.v2_messaging.MessageServiceV2;
import com.dmforu.v2_subscrible.model.dto.DepartmentStatusDTO;
import com.dmforu.v2_subscrible.model.dto.KeywordDTO;
import com.dmforu.v2_subscrible.model.dto.KeywordStatusDTO;
import com.dmforu.v2_subscrible.model.dto.InitTokensDTO;
import com.google.firebase.messaging.FirebaseMessagingException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@Tag(name="알림설정")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/dmu/subscribe")
public class SubscribeController {

    private final SubscribeService subscribeService;

    //Tet URL
    @Operation(summary = "최초 Token 등록 API", description = "애플리케이션 최초 실행시 Token과 학과, 키워드를 등록한다.")
    @PostMapping("/registration")
    public void createTokenDepartment(@RequestBody InitTokensDTO initTokensDto) {
        subscribeService.createInitTokenInformation(initTokensDto);
    }

    @Operation(summary = "Keyword 수정 API", description = "애플리케이션 키워드를 수정 및 추가한다.")
    @PutMapping("/registration")
    public void updateTokenDepartment(@RequestBody KeywordDTO keywordDto) throws ExecutionException, FirebaseMessagingException, InterruptedException {
        subscribeService.updateKeyword(keywordDto);
    }

    @Operation(summary = "학과 알림 상태 API", description = "학과 알림 상태를 변경한다.")
    @PutMapping("/departmentStatus")
    public void deleteDepartment(@RequestBody DepartmentStatusDTO departmentStatusDTO) {
        subscribeService.updateDepartmentSatus(departmentStatusDTO);
    }

    @Operation(summary = "키워드 알림 상태 API", description = "키워드 알림 상태를 수정한다.")
    @PutMapping("/keywordStatus")
    public void updateDepartment(@RequestBody KeywordStatusDTO keywordStatusDTO) {
        subscribeService.updateKeyWordStatus(keywordStatusDTO);
    }

//    //기존 Token TTL Refresh
//    @Operation(summary = "Token 갱신 API", description = "Token 정보를 갱신한다.<br>현재 서비스 버전에서는 사용되지 않는 API이다.")
//    @PostMapping("/refresh")
//    public void refreshTopic(@RequestBody RequestTokenDto tokensDto){
//        tokenService.refreshToken(tokensDto.getToken());
//    }
//
//

//

//
//    //redisService.updateToken = Token 유효시간 갱신 및 Token Redis Server에 등록
//    //messageService.updateToken = Google FCM token & Topic 등록
//    @Operation(summary = "키워드 알림 수정 API", description = "키워드를 수정한다.<br>수정 후, 수정된 키워드에 해당하는 알림들을 받을수 있다.")
//    @PutMapping("/keyword")
//    public void updateTopic(@RequestBody RequestTokenDto tokensDto) throws FirebaseMessagingException {
//        tokenService.updateTopic(
//                new TokensDto(tokensDto.getToken(), tokensDto.getTopic())
//        );
//    }
//
//    //Topic 구독 취소, 추후에 무결성 검증 로직 추가
//    @Operation(summary = "키워드 알림 끄기 API", description = "키워드 알림을 받지 않도록 변경한다.")
//    @DeleteMapping("/keyword")
//    public void deleteTopic(@RequestBody RequestTokenDto tokensDto) {
//        tokenService.deleteTopic(tokensDto.getToken());
//    }
}
