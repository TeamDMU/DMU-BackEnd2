package me.gijung.DMforU.service.parser;

import lombok.RequiredArgsConstructor;
import me.gijung.DMforU.model.domain.Diet;
import me.gijung.DMforU.utils.WebPageLoader;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DietParser implements Parser<Diet> {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");

    @Value("${dmu.url.diet}")
    private String DMU_DIET_URL;

    @Override
    public List<Diet> parse() {

        List<Diet> result = new ArrayList<>();

        Document document = WebPageLoader.getHTML(DMU_DIET_URL);

        Elements rows = document.select("div.table_1 table tbody tr");
        for (Element row : rows) {
            Diet item = parseDietItemFromRow(row);

            if (item != null) {
                result.add(item);
            }
        }

        return result;
    }


    private Diet parseDietItemFromRow(Element row) {
        Elements columns = row.select("th, td");

        // 요일 출력
        Element dayElement = columns.get(0);
        String day = dayElement.text();

        // 짝수 컬럼에는 day 정보가 있는 위치에 "교직원식당"의 정보가 기입됨으로 넘겨야 함
        if ("교직원식당".equals(day)) {
            return null;
        }

        LocalDate parsedDate = LocalDate.parse(day.substring(0, 10), formatter);

        // 코리안 푸드 메뉴가 4번째 컬럼에 작성되기 때문에, 컬럼의 개수가 3개 이하라면 해당 날짜의 메뉴는 존재하지 않는 것으로 처리하였다.
        // 만일 식단의 작성 방법이 변경된다면 해당 로직 또한 변경의 필요성이 존재한다.
        Element menuColumn = columns.size() > 3 ? columns.get(3) : null;
        String menuElement = menuColumn != null ? menuColumn.text() : null;
        String[] menus = !StringUtils.isEmpty(menuElement) ? menuElement.split(", ") : new String[] {};

        return new Diet(parsedDate, menus);
    }
}
