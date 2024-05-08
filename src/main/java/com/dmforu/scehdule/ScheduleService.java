package com.dmforu.scehdule;

import com.dmforu.crawling.parser.Parser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    // TODO: (YearSchedule 필드, 메서드) 애플리케이션 구 버전 전부 업데이트되면 제거 예정
    private final Parser<YearSchedule> legacyScheduleParser;
    private final Parser<Schedule.Year> scheduleParser;

    public List<YearSchedule> getDataLegacy() {
        return legacyScheduleParser.parse();
    }

    public List<Schedule.Year> getData() {
        return scheduleParser.parse();
    }
}
