package com.dmforu.subscribe.v2;


import com.dmforu.subscribe.v2.model.dto.DepartmentStatusDTO;
import com.dmforu.subscribe.v2.model.dto.KeywordDTO;
import com.dmforu.subscribe.v2.model.dto.KeywordStatusDTO;
import com.dmforu.subscribe.v2.model.dto.RepectorTokensDTO;
import com.dmforu.subscribe.v2.service.DepartmentService;
import com.dmforu.subscribe.v2.service.TokenServiceV2;
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
    public void createInitTokenInformation(RepectorTokensDTO repectorTokensDto) {
        tokenServiceV2.createInitTokenInformation(repectorTokensDto);
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
