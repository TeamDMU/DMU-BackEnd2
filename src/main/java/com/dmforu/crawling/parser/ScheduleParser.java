package com.dmforu.crawling.parser;

import com.dmforu.crawling.WebPageLoader;
import com.dmforu.schedule.Schedule;
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
public class ScheduleParser implements Parser<Schedule.Year> {

    private static final String DMU_SCHEDULE_URL = "https://www.dongyang.ac.kr/dongyang/71/subview.do?year=";
    private static final String YEAR_SCHEDULE_SELECTOR = ".yearSchdulWrap";
    private static final String MONTH_DATE_SELECTOR = "p";
    private static final String MONTH_SCHEDULE_SELECTOR = ".scheList li";
    private static final String SCHEDULE_DATE_SELECTOR = "dt span";
    private static final String SCHEDULE_CONTENT_SELECTOR = "dd span";

    @Value("${server.time.zone}")
    private String TIME_ZONE;

    @Override
    public List<Schedule.Year> parse() {
        List<Schedule.Year> schedule = new ArrayList<>();

        int currentYear = LocalDate.now(ZoneId.of(TIME_ZONE)).getYear();

        // 작년부터 내년의 일정을 가져온다.
        for (int year = currentYear - 1; year <= currentYear + 1; year++) {
            List<Schedule.Month> yearSchedule = fetchYearSchedule(year);
            schedule.add(new Schedule.Year(year, yearSchedule));
        }

        return schedule;
    }

    private List<Schedule.Month> fetchYearSchedule(int year) {
        List<Schedule.Month> yearSchedule = new ArrayList<>();

        Document document = WebPageLoader.getHTML(DMU_SCHEDULE_URL + year);

        Elements monthTables = document.select(YEAR_SCHEDULE_SELECTOR);

        for (Element monthTable : monthTables) {
            Schedule.Month monthSchedule = fetchMonthSchedule(monthTable);

            // 일정 정보가 업로드 되지 않는 달은 제외한다. (다음 년도 3월 이후의 정보)
            if (monthSchedule.getMonthSchedule().isEmpty()) break;

            yearSchedule.add(monthSchedule);
        }

        return yearSchedule;
    }

    private Schedule.Month fetchMonthSchedule(Element monthTable) {
        List<Schedule> monthSchedule = new ArrayList<>();

        // <p id="yearmonth20241">2024.1</p> p태그에서 "2024.1"을 문자열로 가져온다.
        // 이때, 월 정보만 필요하기 때문에 문자열 인덱스 5부터의 정보만을 가져온다.
        String monthText = monthTable.select(MONTH_DATE_SELECTOR).first().text().substring(5);
        int month = Integer.parseInt(monthText);

        Elements scheduleTable = monthTable.select(MONTH_SCHEDULE_SELECTOR);
        for (Element schedule : scheduleTable) {
            monthSchedule.add(parseSchedule(schedule));
        }

        return new Schedule.Month(month, monthSchedule);
    }

    /**
     * 다음과 같이 일정을 파싱한다.
     *
     * 01.01 (월)	신정
     * -> ["01.01 (월)", "01.01 (월)"], "신정"
     *
     * 01.03 (수) ~ 01.15 (월)	정시모집 원서 접수
     * -> ["01.03 (수)", "01.15 (월)"], "정시모집 원서 접수"
     */
    private Schedule parseSchedule(Element schedule) {
        String dateText = schedule.select(SCHEDULE_DATE_SELECTOR).text().replaceAll(" ", "");
        String[] dates = dateText.contains("~") ? dateText.split("~") : new String[]{dateText, dateText};

        String content = schedule.select(SCHEDULE_CONTENT_SELECTOR).text();

        return new Schedule(dates, content);
    }
}
