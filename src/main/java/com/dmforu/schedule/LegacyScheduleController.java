package com.dmforu.schedule;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "구버전 학사일정")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/dmu/schedule")
public class LegacyScheduleController {

    private final ScheduleService scheduleService;

    @GetMapping
    public ResponseEntity<List<YearSchedule>> getScheduler() {
        return ResponseEntity.ok().body(scheduleService.getDataLegacy());
    }
}
