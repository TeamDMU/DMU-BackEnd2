package com.dmforu.subscribe;

import com.dmforu.subscribe.service.DepartmentService;
import com.dmforu.subscribe.service.TokenServiceV2;
import com.dmforu.subscribe.dtoV2.*;
import com.google.firebase.messaging.FirebaseMessagingException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@Tag(name="신 버전 알림설정")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/dmu/subscribe")
public class SubscribeController {

    private final DepartmentService departmentService;
    private final TokenServiceV2 tokenServiceV2;


    @PostMapping("/test")
    public void test(@RequestParam String A, @RequestParam String B) {

    }
    //Tet URL
    @Operation(summary = "최초 Token 등록 API", description = "애플리케이션 최초 실행시 Token과 학과, 키워드를 등록한다.")
    @PostMapping("/registration")
    public void createTokenDepartment(@RequestBody InitTokensDTO initTokensDto) {
        tokenServiceV2.createInitTokenInformation(initTokensDto);
    }

    @Operation(summary = "Keyword 수정 API", description = "애플리케이션 키워드를 수정 및 추가한다.")
    @PutMapping("/keyword")
    public void updateTokenDepartment(@RequestBody KeywordDTO keywordDto) throws ExecutionException, FirebaseMessagingException, InterruptedException {
        tokenServiceV2.updateKeyword(keywordDto);
    }

    @Operation(summary = "학과 알림 상태 API", description = "학과 알림 상태를 변경한다.")
    @PutMapping("/departmentStatus")
    public void deleteDepartment(@RequestBody DepartmentStatusDTO departmentStatusDTO) {
        departmentService.updateDepartmentStatus(departmentStatusDTO);
    }

    @Operation(summary = "키워드 알림 상태 API", description = "키워드 알림 상태를 수정한다.")
    @PutMapping("/keywordStatus")
    public void updateDepartmentStatus(@RequestBody KeywordStatusDTO keywordStatusDTO) {
        tokenServiceV2.updateKeywordStatus(keywordStatusDTO);
    }

    @Operation(summary = "학과 수정 API", description = "학과 정보를 수정한다.")
    @PutMapping("/department")
    public void updateDepartment(@RequestBody DepartmentDTO departmentDTO) {
        departmentService.updateDepartment(departmentDTO);
    }


//    //기존 Token TTL Refresh
//    @Operation(summary = "Token 갱신 API", description = "Token 정보를 갱신한다.<br>현재 서비스 버전에서는 사용되지 않는 API이다.")
//    @PostMapping("/refresh")
//    public void refreshTopic(@RequestBody RequestTokenDto tokensDto){
//        tokenService.refreshToken(tokensDto.getToken());
//    }
}
