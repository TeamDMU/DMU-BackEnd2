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
        // 서버의 위치에 따라 날짜가 다를 수 있기 때문에 선언
        // yml으로 해당 정보를 옮겨야 할듯
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
            MonthSchedule monthSchedule;
            monthSchedule = fetchMonthSchedule(monthTable, year);

            // 일정 정보가 업로드 되지 않는 달은 제외한다. (다음 년도 3월 이후의 정보)
            if (monthSchedule.getScheduleEntries().isEmpty()) break;

            yearSchedules.add(monthSchedule);
        }

        return yearSchedules;
    }

    private MonthSchedule fetchMonthSchedule(Element monthTable, int year) {
        String monthText = monthTable.select("p").first().text().substring(5);
        int month = Integer.parseInt(monthText);
        List<SchedulerEntry> monthEntries = new ArrayList<>();

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