package me.gijung.DMforU.service;

import lombok.RequiredArgsConstructor;
import me.gijung.DMforU.config.Major;
import me.gijung.DMforU.model.dto.NoticeDto;
import me.gijung.DMforU.model.entity.Notice;
import me.gijung.DMforU.repository.NoticeRepository;
import me.gijung.DMforU.service.parser.notice.DepartmentNoticeParser;
import me.gijung.DMforU.utils.NoticeMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentNoticeService {

    private final DepartmentNoticeParser parser;
    private final NoticeRepository noticeRepository;

    /**
     * Major 열거형의 모든 값을 반복하여 모든 학과의 공지사항을 크롤링한다. <br>
     * 데이터베이스에 저장된 공지사항이 존재한다면, 최신 공지사항만 크롤링하여 업데이트 한다. <br>
     * 평일 오전 10시, 오후 17시 자동으로 메서드를 실행한다.
     */

    @Scheduled(cron = "0 0 10,17 * * MON-FRI")
    public void crawling() {
        for (Major major : Major.values()) {
            crawlMajorDepartment(major);
        }
    }

    /**
     * 학과 이름을 기반으로 해당 학과의 페이지네이션된 공지사항 목록을 가져온다.
     *
     * @param page       검색할 페이지 번호 (1번부터 시작)
     * @param size       페이지 당 공지사항의 수
     * @param department 학과 이름
     * @return 페이지에 해당하는 공지사항 목록
     */
    public List<NoticeDto> findDepartmentNotices(int page, int size, String department) {
        Page<Notice> departmentNoticePage = noticeRepository.findByType(
                department, PageRequest.of(page - 1, size, Sort.by(Sort.Direction.DESC, "number")));

        return departmentNoticePage.map(NoticeMapper::mapToDto).stream().toList();
    }

    /**
     * 해당 학과의 모든 공지사항을 크롤링한다. <br>
     * 공지사항 첫번째 페이지부터 크롤링을 시작한다. <br>
     * 데이터베이스에 저장된 공지사항의 가장 최신 번호와, 파싱된 공지사항 목록을 saveNewNotices 메서드를 통해 저장할지 결정한다. <br>
     * 만일, 저장이 이뤄지지 않는다면 더이상 파싱할 필요가 없기 때문에 무한 루트에서 빠져나온다. <br>
     *
     * @param major 학과 정보
     */
    private void crawlMajorDepartment(Major major) {
        parser.setMajor(major);
        parser.setPageNumber(1);
        Integer maxNumber = noticeRepository.findMaxNumberByType(major.getName());
        int currentMaxNumber = maxNumber != null ? maxNumber : 0;

        while (true) {
            List<Notice> notices = parser.parse();
            boolean isNewNoticeFound = saveNewNotices(notices, currentMaxNumber);

            if (!isNewNoticeFound) {
                return;
            }
        }
    }

    /**
     * 새로운 학과 공지사항을 저장한다. <br>
     * 데이터베이스에 저장된 최신 공지사항 번호보다 큰 번호를 가진 학과 공지사항을 저장한다. <br>
     * 데이터베이스에 저장된 공지사항이 존재하지 않아 currentMaxNumber가 0이라면 게시글의 번호가 1번이 될 때 까지 저장한다. <br>
     * 위의 조건을 만족하기 전까지 true를 반환하고, 만족하게 되면 false를 반환한다.
     *
     * @param notices           저장할 학과의 공지사항 목록
     * @param currentMaxNumber  현재 데이터베이스에 저장된 최신 공지사항의 번호
     * @return 저장이 성공했다면 true, 그렇지 않다면 false
     */
    private boolean saveNewNotices(List<Notice> notices, int currentMaxNumber) {
        for (Notice notice : notices) {
            if (notice.getNumber() <= currentMaxNumber) {
                return false;
            }

            noticeRepository.save(notice);

            if (notice.getNumber() == 1) {
                return false;
            }
        }

        return true;
    }
}
