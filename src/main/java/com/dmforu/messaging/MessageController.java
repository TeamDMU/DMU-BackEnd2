package com.dmforu.messaging;

import com.dmforu.messaging.dto.SendMessageRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name="알림 전송")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/notification")
public class MessageController {

    private final MessageServiceV2 messageService;

    @Operation(summary = "전체 사용자 알림 발송 API", description = "작성한 제목과 내용의 푸시 알림을 전체 발송한다.")
    @PostMapping
    public ResponseEntity sendMessage(@RequestBody SendMessageRequest request) {

        if (messageService.sendFullMessage(request.getTitle(), request.getContent())) {
            return ResponseEntity.ok("알림 전송 성공");
        }

        return ResponseEntity.badRequest().body("알림 전송 실패");
    }
}
