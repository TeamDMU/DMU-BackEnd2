package me.gijung.DMforU.service;

import lombok.RequiredArgsConstructor;
import me.gijung.DMforU.model.domain.schedule.YearSchedule;
import me.gijung.DMforU.model.dto.SchedulerDto;
import me.gijung.DMforU.service.parser.HTMLParser;

import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class SchedulerService implements CrawlingService<SchedulerDto> {

    private final HTMLParser<YearSchedule> parser;

    public SchedulerDto getData() {
        return new SchedulerDto(parser.Parsing());
    }
}
