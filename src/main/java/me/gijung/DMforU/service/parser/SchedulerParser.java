package me.gijung.DMforU.service.parser;

import lombok.RequiredArgsConstructor;
import me.gijung.DMforU.model.domain.schedule.MonthSchedule;
import me.gijung.DMforU.model.domain.schedule.SchedulerEntry;
import me.gijung.DMforU.model.domain.schedule.YearSchedule;
import me.gijung.DMforU.utils.WebPageLoader;
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
public class SchedulerParser implements Parser<YearSchedule> {

    @Value("${server.time.zone}")
    private String TIME_ZONE;

    @Value("${dmu.url.scheduler}")
    private String DMU_SCHEDULER_URL;

    @Override
    public List<YearSchedule> parse() {
        List<YearSchedule> yearSchedules = new ArrayList<>();

        int currentYear = LocalDate.now(ZoneId.of(TIME_ZONE)).getYear();

        // 올해의 일정을 파싱하고 저장
        List<MonthSchedule> currentYearSchedules = fetchSchedulesForYear(currentYear);
        yearSchedules.add(new YearSchedule(currentYear, currentYearSchedules));

        // 내년의 일정을 파싱하고 저장 -> 내년 2월 까지의 일정이 업로드 되기 때문
        List<MonthSchedule> nextYearSchedules = fetchSchedulesForYear(currentYear + 1);
        yearSchedules.add(new YearSchedule(currentYear + 1, nextYearSchedules));

        return yearSchedules;
    }

    private List<MonthSchedule> fetchSchedulesForYear(int year) {
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

        List<SchedulerEntry> monthEntries = new ArrayList<>();

        // <p id="yearmonth20241">2024.1</p> p태그에서 "2024.1"을 문자열로 가져온다.
        // 이때, 월 정보만 필요하기 때문에 문자열 인덱스 5부터의 정보만을 가져온다.
        String monthText = monthTable.select("p").first().text().substring(5);
        int month = Integer.parseInt(monthText);

        Elements scheduleList = monthTable.select(".scheList li");
        for (Element schedule : scheduleList) {
            SchedulerEntry schedulerEntry = parseScheduleEntry(schedule);
            monthEntries.add(schedulerEntry);
        }

        return new MonthSchedule(month, monthEntries);
    }

    private SchedulerEntry parseScheduleEntry(Element schedule) {
        String dateText = schedule.select("dt span").text().replaceAll(" ", "");
        String[] dates = dateText.contains("~") ? dateText.split("~") : new String[]{dateText, dateText};

        String content = schedule.select("dd span").text();

        return new SchedulerEntry(dates, content);
    }

}