package com.dmforu.cafeteria;

import com.dmforu.crawling.parser.Parser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CafeteriaService {

    private final Parser<Diet> cafeteriaParser;

    public List<Diet> getData() {
        return cafeteriaParser.parse();
    }
}
