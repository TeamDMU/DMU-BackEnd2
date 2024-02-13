package me.gijung.DMforU.service;

import lombok.RequiredArgsConstructor;
import me.gijung.DMforU.model.domain.schedule.YearSchedule;
import me.gijung.DMforU.service.parser.HTMLParser;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class SchedulerService implements CrawlingService<List<YearSchedule>> {

    private final HTMLParser<YearSchedule> parser;

    public List<YearSchedule> getData() {
        return parser.Parsing();
    }
}
