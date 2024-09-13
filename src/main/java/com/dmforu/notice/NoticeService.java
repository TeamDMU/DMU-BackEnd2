package com.dmforu.notice;

import com.dmforu.notice.dto.NoticeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;

    /**
     * 키워드를 기반으로 해당 페이지네이션된 전체 공지사항 목록을 가져온다.
     *
     * @param searchWord 검색 키워드
     * @param department 학과명
     * @param page       검색할 페이지 번호 (1번부터 시작)
     * @param size       페이지당 공지사항의 수
     * @return 페이지에 해당하는 공지사항 목록
     */
    public List<NoticeRequest> findNoticesBySearchWord(String searchWord, String department, int page, int size) {
        Page<Notice> result = noticeRepository.findBySearchWordAndDepartment(searchWord, department,
                PageRequest.of(page - 1, size, Sort.by(Sort.Direction.DESC, "date")));

        return result.map(NoticeRequest::toDto).getContent();
    }

    /**
     * 학과 이름을 기반으로 해당 학과의 페이지네이션된 공지사항 목록을 가져온다.
     *
     * @param page       검색할 페이지 번호 (1번부터 시작)
     * @param size       페이지 당 공지사항의 수
     * @param department 학과 이름
     * @return 페이지에 해당하는 공지사항 목록
     */
    public List<NoticeRequest> findDepartmentNotices(int page, int size, String department) {
        Page<Notice> departmentNoticePage = noticeRepository.findByType(
                department, PageRequest.of(page - 1, size, Sort.by(Sort.Direction.DESC, "number")));

        return departmentNoticePage.map(NoticeRequest::toDto).stream().toList();
    }

    /**
     * 페이지네이션을 적용하여 대학 공지사항 목록을 가져온다.
     *
     * @param page 검색할 페이지 번호 (1번부터 시작)
     * @param size 페이지 당 공지사항의 수
     * @return 페이지에 해당하는 공지사항 목록
     */
    public List<NoticeRequest> findUniversityNotices(int page, int size) {
        Page<Notice> universityNoticePage = noticeRepository.findByType("대학",
                PageRequest.of(page - 1, size, Sort.by(Sort.Direction.DESC, "number")));
        return universityNoticePage.map(NoticeRequest::toDto).stream().toList();
    }
}
