package com.dmforu.subscribe;

import com.dmforu.subscribe.dto.DepartmentDto;
import com.dmforu.subscribe.dto.InitRequestDto;
import com.dmforu.subscribe.dto.RequestTokenDto;
import com.dmforu.subscribe.service.Department;
import com.dmforu.subscribe.service.InitService;
import com.dmforu.subscribe.service.TokenService;
import com.google.firebase.messaging.FirebaseMessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/dmu/subscribe")
public class SubscribeController {

    private final InitService initService;
    private final Department departmentService;
    private final TokenService tokenService;


    @PostMapping("/registration")
    public void createTokenDepartment(@RequestBody InitRequestDto requestInitDto) {
        initService.createTokenDepartment(requestInitDto);
    }

    //기존 Token TTL Refresh
    @PostMapping("/refresh")
    public void refreshTopic(@RequestBody RequestTokenDto tokensDto){
        tokenService.refreshToken(tokensDto);
    }

    @PutMapping("/department")
    public void updateDepartment(@RequestBody DepartmentDto departmentDto) {
        departmentService.updateDepartment(departmentDto);
    }

    @DeleteMapping("/department")
    public void deleteDepartment(@RequestBody DepartmentDto departmentDto) {
        departmentService.deleteDepartment(departmentDto);
    }

    //redisService.updateToken = Token 유효시간 갱신 및 Token Redis Server에 등록
    //messageService.updateToken = Google FCM token & Topic 등록
    @PutMapping("/keyword")
    public void updateTopic(@RequestBody RequestTokenDto tokensDto) throws FirebaseMessagingException, ExecutionException, InterruptedException {
        tokenService.updateTopic(tokensDto);
    }

    //Topic 구독 취소, 추후에 무결성 검증 로직 추가
    @DeleteMapping("/keyword")
    public void deleteTopic(@RequestBody RequestTokenDto tokensDto) {
        tokenService.deleteTopic(tokensDto);
    }
}
