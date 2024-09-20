package com.dmforu.schedule

import io.swagger.v3.oas.annotations.media.Schema

data class Schedule(
        @Schema(description = "날짜", example = "[01.01(일), 01.01(일)]")
        val date: Array<String>,

        @Schema(description = "내용", example = "신정")
        val content: String
) {
    data class Month (
        @Schema(description = "월", example = "1")
        val month: Int,
        val monthSchedule: List<Schedule>
    )

    data class Year (
        @Schema(description = "년도", example = "2024")
        val year: Int,
        val yearSchedule: List<Month>
    )
}
