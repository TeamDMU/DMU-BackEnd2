package me.gijung.DMforU.service;

import lombok.RequiredArgsConstructor;
import me.gijung.DMforU.model.domain.WeeklyMenu;

import me.gijung.DMforU.service.parser.Parser;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class MenuService implements CrawlingService<List<WeeklyMenu>> {

    private final Parser<WeeklyMenu> parser;

    public List<WeeklyMenu> getData() {
        return parser.parse();
    }
}
