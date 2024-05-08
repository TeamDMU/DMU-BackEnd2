package com.dmforu.scehdule;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "학사일정")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/schedule")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @Operation(summary = "학사일정 API", description = "현재년도를 기준으로 작년부터 내년 2월까지의 학사일정을 출력한다.")
    @GetMapping("/v1")
    public ResponseEntity<List<Schedule.Year>> getSchedule() {
        return ResponseEntity.ok().body(scheduleService.getData());
    }
}