package me.gijung.DMforU.service.parser.notice;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import me.gijung.DMforU.model.entity.Notice;
import me.gijung.DMforU.service.parser.Parser;
import me.gijung.DMforU.utils.WebPageLoader;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@Setter
@RequiredArgsConstructor
public class UniversityNoticeParser extends UrlGenerator implements Parser<Notice> {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy.MM.dd");

    private static final String SEARCH_URL = "https://www.dongyang.ac.kr/dongyang/129/subview.do?page=%d";
    private static final String RESULT_URL = "https://www.dongyang.ac.kr%s?layout=unknown";
    private static final String SUFFIX_NEW_POST = "새글";
    private static final String NOTICE_TYPE = "대학";

    private int pageNumber;

    /**
     * HTML을 파싱하여 대학 공지사항 목록을 반환한다.
     *
     * @return 대학 공지사항 목록
     */
    @Override
    public List<Notice> parse() {

        List<Notice> universityNotices = new ArrayList<>();

        Document document = WebPageLoader.getHTML(generateSearchUrl());

        Elements rows = document.select(".board-table tbody tr");

        for (Element row : rows) {

            int number;

            try {
                number = Integer.parseInt(row.select(".td-num").text());
            } catch (NumberFormatException e) {
                continue;
            }

            String title = removeSuffix(row.select(".td-subject a").text(), SUFFIX_NEW_POST);
            String author = row.select(".td-write").text();
            String url = row.select(".td-subject a").attr("href");
            LocalDate date = LocalDate.parse(row.select(".td-date").text(), DATE_FORMATTER);

            Notice universityNotice = new Notice().builder()
                    .type(NOTICE_TYPE)
                    .number(number)
                    .date(date)
                    .title(title)
                    .author(author)
                    .url(generateUrlFromSearch(url))
                    .build();

            universityNotices.add(universityNotice);
        }

        return universityNotices;
    }

    /**
     * 파싱할 페이지의 URL을 생성한다.
     *
     * @return URL
     */
    @Override
    protected String generateSearchUrl() {
        return String.format(SEARCH_URL, pageNumber++);
    }

    /**
     * 파싱 결과를 통해 공지사항의 URL를 생성한다.
     *
     * @param url URL 파싱결과
     * @return 공지사항 URL
     */
    @Override
    protected String generateUrlFromSearch(String url) {
        return String.format(RESULT_URL, url);
    }

    /**
     * 접미사를 제거한다.
     *
     * @param title  원본 문자열
     * @param suffix 제거하고 싶은 접미사
     * @return 원본에서 접미사를 제거한 문자열
     */
    private String removeSuffix(String title, String suffix) {

        if (title.endsWith(suffix)) {
            return title.substring(0, title.length() - suffix.length()).trim();
        }

        return title;
    }
}
