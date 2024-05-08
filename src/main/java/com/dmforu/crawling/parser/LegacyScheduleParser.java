package com.dmforu.crawling.parser;

import com.dmforu.crawling.WebPageLoader;
import com.dmforu.scehdule.MonthSchedule;
import com.dmforu.scehdule.Schedule;
import com.dmforu.scehdule.YearSchedule;
import lombok.RequiredArgsConstructor;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LegacyScheduleParser implements Parser<YearSchedule> {

    @Value("${server.time.zone}")
    private String TIME_ZONE;

    @Value("${dmu.url.scheduler}")
    private String DMU_SCHEDULER_URL;

    @Override
    public List<YearSchedule> parse() {
        List<YearSchedule> yearSchedules = new ArrayList<>();

        int currentYear = LocalDate.now(ZoneId.of(TIME_ZONE)).getYear();

        // 작년부터 내년의 일정을 가져온다.
        for (int year = currentYear - 1; year <= currentYear + 1; year++) {
            List<MonthSchedule> schedules = fetchYearSchedule(year);
            yearSchedules.add(new YearSchedule(year, schedules));
        }

        return yearSchedules;
    }

    private List<MonthSchedule> fetchYearSchedule(int year) {
        List<MonthSchedule> yearSchedules = new ArrayList<>();
        Document document = WebPageLoader.getHTML(DMU_SCHEDULER_URL + year);

        Elements monthTables = document.select(".yearSchdulWrap");

        for (Element monthTable : monthTables) {
            MonthSchedule monthSchedule = fetchMonthSchedule(monthTable);

            // 일정 정보가 업로드 되지 않는 달은 제외한다. (다음 년도 3월 이후의 정보)
            if (monthSchedule.getScheduleEntries().isEmpty()) break;

            yearSchedules.add(monthSchedule);
        }

        return yearSchedules;
    }

    private MonthSchedule fetchMonthSchedule(Element monthTable) {
        List<Schedule> monthEntries = new ArrayList<>();

        // <p id="yearmonth20241">2024.1</p> p태그에서 "2024.1"을 문자열로 가져온다.
        // 이때, 월 정보만 필요하기 때문에 문자열 인덱스 5부터의 정보만을 가져온다.
        String monthText = monthTable.select("p").first().text().substring(5);
        int month = Integer.parseInt(monthText);

        Elements scheduleList = monthTable.select(".scheList li");
        for (Element schedule : scheduleList) {
            Schedule scheduleEntry = parseSchedule(schedule);
            monthEntries.add(scheduleEntry);
        }

        return new MonthSchedule(month, monthEntries);
    }

    private Schedule parseSchedule(Element schedule) {
        String dateText = schedule.select("dt span").text().replaceAll(" ", "");
        String[] dates = dateText.contains("~") ? dateText.split("~") : new String[]{dateText, dateText};

        String content = schedule.select("dd span").text();

        return new Schedule(dates, content);
    }

}