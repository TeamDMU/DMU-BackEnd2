package com.dmforu.schedule;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LegacySchedule {
    @Schema(description = "날짜", example = "[01.01(일), 01.01(일)]")
    private String[] date;

    @Schema(description = "내용", example = "신정")
    private String content;
}
