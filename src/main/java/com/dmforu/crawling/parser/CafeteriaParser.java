package com.dmforu.crawling.parser;

import com.dmforu.cafeteria.Diet;
import com.dmforu.crawling.WebPageLoader;
import lombok.RequiredArgsConstructor;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CafeteriaParser implements Parser<Diet> {

    private static final String DMU_DIET_URL = "https://www.dongyang.ac.kr/diet/dongyang/1/view.do";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy.MM.dd");
    private static final String TABLE_SELECTOR = "div.table_1 table tbody tr";
    private static final String DATA_SELECTOR = "th, td";
    private static final String MENU_SEPARATOR = ", ";
    private static final String PASS_COLUMN = "교직원식당";

    @Override
    public List<Diet> parse() {

        List<Diet> diets = new ArrayList<>();

        Document document = WebPageLoader.getHTML(DMU_DIET_URL);

        Elements rows = document.select(TABLE_SELECTOR);
        for (Element row : rows) {
            Diet diet = parseDiet(row);

            if (diet != null) {
                diets.add(diet);
            }
        }

        return diets;
    }


    private Diet parseDiet(Element row) {
        Elements columns = row.select(DATA_SELECTOR);

        // 요일 출력
        Element dayElement = columns.get(0);
        String day = dayElement.text();

        // 짝수 컬럼에는 day 정보가 있는 위치에 "교직원식당"의 정보가 기입됨으로 넘겨야 함
        if (PASS_COLUMN.equals(day)) {
            return null;
        }

        LocalDate parsedDate = LocalDate.parse(day.substring(0, 10), DATE_FORMATTER);

        // 코리안 푸드 메뉴가 4번째 컬럼에 작성되기 때문에, 컬럼의 개수가 3개 이하라면 해당 날짜의 메뉴는 존재하지 않는 것으로 처리하였다.
        // 만일 식단의 작성 방법이 변경된다면 해당 로직 또한 변경의 필요성이 존재한다.
        Element menuColumn = columns.size() > 3 ? columns.get(3) : null;
        String menuElement = menuColumn != null ? menuColumn.text() : null;
        String[] menus = !StringUtils.isEmpty(menuElement) ? menuElement.split(MENU_SEPARATOR) : new String[] {};

        return new Diet(parsedDate, menus);
    }
}
