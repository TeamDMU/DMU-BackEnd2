package me.gijung.DMforU.service.parser;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import me.gijung.DMforU.config.Major;
import me.gijung.DMforU.model.entity.Notice;
import me.gijung.DMforU.utils.WebPageLoader;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Setter
@RequiredArgsConstructor
public class DepartmentNoticeParser extends UrlGenerator implements Parser<Notice> {

    private static final Pattern pattern = Pattern.compile("\\('([^']+)'\\,'([^']+)'\\,'([^']+)'\\,'([^']+)'");
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");

    private Major major;
    private int pageNumber;


    /**
     * HTML을 파싱하여 학과 공지사항 목록을 반환한다.
     *
     * @return 학과 공지사항 목록
     */
    @Override
    public List<Notice> parse() {

        List<Notice> departmentNotices = new ArrayList<>();

        Document document = WebPageLoader.getHTML(generateSearchUrl());

        Elements rows = document.select(".board-table tbody tr");

        for (Element row : rows) {

            int number;

            try {
                number = Integer.parseInt(row.select(".td-num").text());
            } catch (NumberFormatException e) {
                continue;
            }

            String title = row.select(".td-subject a").text();
            String author = row.select(".td-write").text();
            String url = row.select(".td-subject a").attr("href");
            LocalDate date = LocalDate.parse(row.select(".td-date").text(), formatter);

            Notice departmentNotice = Notice.builder()
                    .notice_type(major.getName())
                    .number(number)
                    .date(date)
                    .title(title)
                    .author(author)
                    .url(generateUrlFromSearch(url))
                    .build();

            departmentNotices.add(departmentNotice);
        }

        return departmentNotices;
    }

    /**
     * 파싱할 페이지의 URL을 생성한다.
     *
     * @return URL
     */
    @Override
    protected String generateSearchUrl() {
        return String.format("https://www.dongyang.ac.kr/%s/%s/subview.do?page=%d",
                major.getMajorCode(), major.getNoticeCode(), pageNumber++);
    }

    /**
     * 파싱 결과를 통해 공지사항의 URL을 생성한다.
     *
     * @param url 파싱 결과
     * @return URL
     * @throws IllegalArgumentException Matcher를 통해 URL을 생성할 수 없는 경우 예외 발생
     */
    @Override
    protected String generateUrlFromSearch(String url) {

        // URL에서 정보를 추출하기 위한 Matcher 생성
        Matcher matcher = pattern.matcher(url);

        if (!matcher.find()) {
            throw new IllegalArgumentException("Matcher did not find any match");
        }

        return String.format("https://www.dongyang.ac.kr/combBbs/%s/%s/%s/view.do?layout=unknown",
                matcher.group(1), matcher.group(2), matcher.group(4));
    }
}