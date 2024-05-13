package com.dmforu.subscribe;

import com.dmforu.subscribe.dto.DepartmentDto;
import com.dmforu.subscribe.dto.InitRequestDto;
import com.dmforu.subscribe.dto.RequestTokenDto;
import com.dmforu.subscribe.dto.TokensDto;
import com.dmforu.subscribe.service.Department;
import com.dmforu.subscribe.service.InitService;
import com.dmforu.subscribe.service.TokenService;
import com.google.firebase.messaging.FirebaseMessagingException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@Tag(name="알림설정")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/dmu/subscribe")
public class SubscribeController {

    private final InitService initService;
    private final Department departmentService;
    private final TokenService tokenService;

    @Operation(summary = "최초 Token 등록 API", description = "애플리케이션 최초 실행시 Token과 학과, 키워드를 등록한다.")
    @PostMapping("/registration")
    public void createTokenDepartment(@RequestBody InitRequestDto requestInitDto) {
        initService.createTokenDepartment(requestInitDto);
    }

    //기존 Token TTL Refresh
    @Operation(summary = "Token 갱신 API", description = "Token 정보를 갱신한다.<br>현재 서비스 버전에서는 사용되지 않는 API이다.")
    @PostMapping("/refresh")
    public void refreshTopic(@RequestBody RequestTokenDto tokensDto){
        tokenService.refreshToken(tokensDto.getToken());
    }


    @Operation(summary = "학과 알림 수정 API", description = "학과를 수정한다.<br>수정 후, 수정된 학과에 해당하는 알림들을 받을수 있다.")
    @PutMapping("/department")
    public void updateDepartment(@RequestBody DepartmentDto departmentDto) {
        departmentService.updateDepartment(departmentDto);
    }

    @Operation(summary = "학과 알림 끄기 API", description = "학과 알림을 받지 않도록 변경한다.")
    @DeleteMapping("/department")
    public void deleteDepartment(@RequestBody DepartmentDto departmentDto) {
        departmentService.deleteDepartment(departmentDto.getToken());
    }

    //redisService.updateToken = Token 유효시간 갱신 및 Token Redis Server에 등록
    //messageService.updateToken = Google FCM token & Topic 등록
    @Operation(summary = "키워드 알림 수정 API", description = "키워드를 수정한다.<br>수정 후, 수정된 키워드에 해당하는 알림들을 받을수 있다.")
    @PutMapping("/keyword")
    public void updateTopic(@RequestBody RequestTokenDto tokensDto) throws FirebaseMessagingException {
        tokenService.updateTopic(
                new TokensDto(tokensDto.getToken(), tokensDto.getTopic())
        );
    }

    //Topic 구독 취소, 추후에 무결성 검증 로직 추가
    @Operation(summary = "키워드 알림 끄기 API", description = "키워드 알림을 받지 않도록 변경한다.")
    @DeleteMapping("/keyword")
    public void deleteTopic(@RequestBody RequestTokenDto tokensDto) {
        tokenService.deleteTopic(tokensDto.getToken());
    }
}
