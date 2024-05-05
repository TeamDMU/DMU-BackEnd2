package com.dmforu.cafeteria;

import com.dmforu.crawling.parser.Parser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CafeteriaService {

    private final Parser<WeeklyMenu> cafeteriaParser;

    public List<WeeklyMenu> getData() {
        return cafeteriaParser.parse();
    }
}
