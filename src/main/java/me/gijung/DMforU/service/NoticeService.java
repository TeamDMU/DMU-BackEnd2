package me.gijung.DMforU.service;

import lombok.RequiredArgsConstructor;
import me.gijung.DMforU.model.dto.NoticeDto;
import me.gijung.DMforU.model.entity.Notice;
import me.gijung.DMforU.repository.NoticeRepository;
import me.gijung.DMforU.utils.NoticeMapper;
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
     * @param page    검색할 페이지 번호 (1번부터 시작)
     * @param size    페이지당 공지사항의 수
     * @return 페이지에 해당하는 공지사항 목록
     */
    public List<NoticeDto> getNotices(String searchWord, String department, int page, int size) {
        Page<Notice> result = noticeRepository.findByTitleContainingSearchWordAndDepartment(searchWord, department,
                PageRequest.of(page - 1, size, Sort.by(Sort.Direction.DESC, "date")));

        return result.map(NoticeMapper::mapToDto).getContent();
    }

}