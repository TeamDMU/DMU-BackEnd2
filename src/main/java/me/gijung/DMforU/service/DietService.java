package me.gijung.DMforU.service;

import lombok.RequiredArgsConstructor;
import me.gijung.DMforU.model.domain.Diet;

import me.gijung.DMforU.service.parser.Parser;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class DietService implements CrawlingService<List<Diet>> {

    private final Parser<Diet> parser;

    public List<Diet> getData() {
        return parser.parse();
    }
}
