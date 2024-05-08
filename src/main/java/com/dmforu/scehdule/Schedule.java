package com.dmforu.scehdule;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Schedule {

    @Schema(description = "날짜", example = "[01.01(일), 01.01(일)]")
    private String[] date;

    @Schema(description = "내용", example = "신정")
    private String content;

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Month {

        @Schema(description = "월", example = "1")
        private int month;
        private List<Schedule> monthSchedule;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Year {

        @Schema(description = "년도", example = "2024")
        private int year;
        private List<Month> yearSchedule;
    }
}
