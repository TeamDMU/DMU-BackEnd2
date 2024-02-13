package me.gijung.DMforU.service.parser;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import me.gijung.DMforU.config.Major;
import me.gijung.DMforU.model.entity.DepartmentNotice;
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
public class DepartmentNoticeParser implements HTMLParser<DepartmentNotice> {


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
    public List<DepartmentNotice> Parsing() {

        List<DepartmentNotice> departmentNotices = new ArrayList<>();

        Document document = WebPageLoader.getHTML(generateSearchUrl());

        Elements rows = document.select(".board-table tbody tr");
        // 각 행을 순회하면서 공지사항 정보를 파싱한다.
        for (Element row : rows) {
            String checkNumber = row.select(".td-num").text();

            // 게시글 번호가 비어있거나, 일반 공지인 경우 스킵한다.
            if (checkNumber.isEmpty() || checkNumber.equals("일반공지")) {
                continue;
            }

            int number = Integer.parseInt(checkNumber);
            String title = row.select(".td-subject a").text();
            String author = row.select(".td-write").text();
            String url = row.select(".td-subject a").attr("href");
            LocalDate date = LocalDate.parse(row.select(".td-date").text(), formatter);

            // URL에서 정보를 추출하기 위한 Matcher 생성
            Matcher matcher = pattern.matcher(url);

            DepartmentNotice departmentNotice = DepartmentNotice.builder()
                    .department(major.getName())
                    .number(number)
                    .date(date)
                    .title(title)
                    .author(author)
                    .url(generateUrlFromSearch(matcher))
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
    private String generateSearchUrl() {
        return String.format("https://www.dongyang.ac.kr/%s/%s/subview.do?page=%d",
                major.getMajorCode(), major.getNoticeCode(), pageNumber++);
    }

    /**
     * 파싱 결과를 통해 공지사항의 URL을 생성한다.
     *
     * @param matcher URL에서 정보를 추출하기 위한 Matcher
     * @return URL
     * @throws IllegalArgumentException Matcher를 통해 URL을 생성할 수 없는 경우 예외 발생
     */
    private String generateUrlFromSearch(Matcher matcher) {
        if (!matcher.find()) {
            throw new IllegalArgumentException("Matcher did not find any match");
        }

        return String.format("https://www.dongyang.ac.kr/combBbs/%s/%s/%s/view.do?layout=unknown",
                matcher.group(1), matcher.group(2), matcher.group(4));
    }
}