package com.dmforu.schedule

import com.dmforu.crawling.parser.Parser
import org.springframework.stereotype.Service

// TODO: (YearSchedule 필드, 메서드) 애플리케이션 구 버전 전부 업데이트되면 제거 예정
@Service
class ScheduleService(
        private val legacyScheduleParser: Parser<YearSchedule>,
        private val scheduleParser: Parser<Schedule.Year>
) {
    fun getDataLegacy(): List<YearSchedule> {
        return legacyScheduleParser.parse()
    }

    fun getData(): List<Schedule.Year> {
        return scheduleParser.parse()
    }
}
