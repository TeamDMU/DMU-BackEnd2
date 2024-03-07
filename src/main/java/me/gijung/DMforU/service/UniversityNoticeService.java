package me.gijung.DMforU.service;

import lombok.RequiredArgsConstructor;
import me.gijung.DMforU.model.dto.NoticeDto;
import me.gijung.DMforU.model.entity.Notice;
import me.gijung.DMforU.repository.NoticeRepository;
import me.gijung.DMforU.service.parser.notice.UniversityNoticeParser;
import me.gijung.DMforU.utils.NoticeMapper;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UniversityNoticeService {

    private final UniversityNoticeParser parser;
    private final NoticeRepository noticeRepository;

    private final ApplicationEventPublisher eventPublisher;

    /**
     * 모든 대학 공지사항을 크롤링한다. <br>
     * 데이터베이스에 저장된 공지사항이 존재한다면, 최신 공지사항만 크롤링하여 업데이트 한다. <br>
     * 평일 오전 10시, 오후 17시 자동으로 메서드를 실행한다.
     */
    @Scheduled(cron = "0 0 10,17 * * MON-FRI")
    @Transactional
    public void crawling() {
        parser.setPageNumber(1);
        Integer maxNumber = noticeRepository.findMaxNumberByType("대학");
        int currentMaxNumber = maxNumber != null ? maxNumber : 0;

        while (true) {
            List<Notice> departmentNotices = parser.parse();
            boolean isNewNoticeFound = saveNewNotices(departmentNotices, currentMaxNumber);
            if (!isNewNoticeFound) {
                return;
            }
        }
    }

    /**
     * 페이지네이션을 적용하여 대학 공지사항 목록을 가져온다.
     *
     * @param page 검색할 페이지 번호 (1번부터 시작)
     * @param size 페이지 당 공지사항의 수
     * @return 페이지에 해당하는 공지사항 목록
     */
    public List<NoticeDto> findUniversityNotices(int page, int size) {
        Page<Notice> universityNoticePage = noticeRepository.findByType("대학",
                PageRequest.of(page - 1, size, Sort.by(Sort.Direction.DESC, "number")));
        return universityNoticePage.map(NoticeMapper::mapToDto).stream().toList();
    }


    /**
     * 새로운 대학 공지사항을 저장한다. <br>
     * 데이터베이스에 저장된 최신 공지사항 번호보다 큰 번호를 가진 학과 공지사항을 저장한다. <br>
     * 데이터베이스에 저장된 공지사항이 존재하지 않아 currentMaxNumber가 0이라면 게시글의 번호가 1번이 될 때 까지 저장한다. <br>
     * 위의 조건을 만족하기 전까지 true를 반환하고, 만족하게 되면 false를 반환한다.
     *
     * @param notices 저장할 대학 공지사항 목록
     * @param currentMaxNumber 현재 데이터베이스에 저장된 최신 공지사항의 번호
     * @return 저장에 성공했다면 true, 그렇지 않다면 false
     */
    private boolean saveNewNotices(List<Notice> notices, int currentMaxNumber) {
        for (Notice notice : notices) {
            if (notice.getNumber() <= currentMaxNumber) {
                return false;
            }
            noticeRepository.save(notice);
            eventPublisher.publishEvent(notice);
            if (notice.getNumber() == 1) {
                return false;
            }
        }

        return true;
    }

}
