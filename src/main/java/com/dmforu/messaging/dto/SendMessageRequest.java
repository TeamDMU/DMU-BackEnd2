package com.dmforu.messaging.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class SendMessageRequest {

    @Schema(description = "알림 제목", example = "[긴급] 점검 예정 알림 전송", required = true)
    private String title;

    @Schema(description = "알림 내용", example = "금일 23:30 ~ 24:00까지 서버 안정화 및 업데이트가 있을 예정입니다.", required = true)
    private String content;

}
