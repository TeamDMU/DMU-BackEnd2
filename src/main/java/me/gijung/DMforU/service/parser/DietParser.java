package me.gijung.DMforU.service.parser;

import lombok.RequiredArgsConstructor;
import me.gijung.DMforU.model.domain.Diet;
import me.gijung.DMforU.utils.WebPageLoader;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DietParser implements HTMLParser<Diet>{

    @Value("${dmu.url.diet}")
    private String DMU_DIET_URL;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");

    @Override
    public List<Diet> Parsing() {
        Document document = WebPageLoader.getHTML(DMU_DIET_URL);

        Elements rows = document.select("div.table_1 table tbody tr");

        List<Diet> result = new ArrayList<>();

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


        Element menuElement = (columns.size() > 3) ? columns.get(3) : null;
        String menus = (menuElement != null && !menuElement.text().isEmpty()) ? menuElement.text() : "없음";

        Diet item = new Diet();
        item.setDate(parsedDate);
        item.setMenus(menus);

        return item;
    }
}
