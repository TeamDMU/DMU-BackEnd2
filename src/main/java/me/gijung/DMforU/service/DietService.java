package me.gijung.DMforU.service;

import lombok.RequiredArgsConstructor;
import me.gijung.DMforU.model.domain.Diet;
import me.gijung.DMforU.model.dto.DietDto;

import me.gijung.DMforU.service.parser.HTMLParser;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class DietService implements CrawlingService<DietDto> {

    private final HTMLParser<Diet> parser;

    public DietDto getData() {
        return new DietDto(parser.Parsing());
    }
}
