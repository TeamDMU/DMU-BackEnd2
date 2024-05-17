package com.dmforu.v2_subscrible;


import com.dmforu.v2_subscrible.model.dto.DepartmentStatusDTO;
import com.dmforu.v2_subscrible.model.dto.KeywordDTO;
import com.dmforu.v2_subscrible.model.dto.KeywordStatusDTO;
import com.dmforu.v2_subscrible.model.dto.InitTokensDTO;
import com.dmforu.v2_subscrible.service.DepartmentService;
import com.dmforu.v2_subscrible.service.TokenServiceV2;
import com.google.firebase.messaging.FirebaseMessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class SubscribeService {


    private final TokenServiceV2 tokenServiceV2;
    private final DepartmentService departmentService;

    // 애플리케이션 최초 Token 정보 등록
    public void createInitTokenInformation(InitTokensDTO initTokensDto) {
        tokenServiceV2.createInitTokenInformation(initTokensDto);
    }

    // 키워드 수정 및 추가
    public void updateKeyword(KeywordDTO keywordDto) throws ExecutionException, FirebaseMessagingException, InterruptedException {
        tokenServiceV2.updateKeyword(keywordDto);
    }

    public void updateDepartmentSatus(DepartmentStatusDTO departmentStatusDTO) {
        departmentService.updateDepartmentStatus(departmentStatusDTO);
    }

    public void updateKeyWordStatus(KeywordStatusDTO keywordStatusDTO) {
        tokenServiceV2.updateKeyWordStatus(keywordStatusDTO);
    }
}
