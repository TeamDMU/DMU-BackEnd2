package com.dmforu.schedule

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "학사일정")
@RestController
@RequestMapping("/api/v1/schedule")
class ScheduleController(
        private val scheduleService: ScheduleService
) {
    @GetMapping
    @Operation(summary = "학사일정 API", description = "현재년도를 기준으로 작년부터 내년 2월까지의 학사일정을 출력한다.")
    fun getSchedule(): ResponseEntity<List<Schedule.Year>> {
        return ResponseEntity.ok().body(scheduleService.getData())
    }
}