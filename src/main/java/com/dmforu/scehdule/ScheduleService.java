package com.dmforu.scehdule;

import com.dmforu.crawling.parser.Parser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final Parser<YearSchedule> scheduleParser;

    public List<YearSchedule> getData() {
        return scheduleParser.parse();
    }
}
