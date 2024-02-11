package me.gijung.DMforU.service;

import lombok.RequiredArgsConstructor;
import me.gijung.DMforU.model.dto.NoticeDto;
import me.gijung.DMforU.repository.NoticeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;

    /**
     * 키워드를 기반으로 해당 페이지네이션된 전체 공지사항 목록을 가져온다.
     *
     * @param keyword 검색 키워드
     * @param page    검색할 페이지 번호 (1번부터 시작)
     * @param size    페이지당 공지사항의 수
     * @return 페이지에 해당하는 공지사항 목록
     */
    public List<NoticeDto> getNotices(String keyword, int page, int size) {
        Page<Object> result = noticeRepository.searchByTitleIgnoringSpaces(keyword,
                PageRequest.of(page - 1, size, Sort.by(Sort.Direction.DESC, "date")));

        return result.map(this::mapToDto).getContent();
    }

    /**
     * 대학 또는 학과 공지의 date, title, author, url 필드로 이뤄진 object를 dto로 변환한다.
     *
     * @param obj 검색된 Object(date, title, author, url 순으로 Object[]로 이뤄짐)
     * @return 공지사항 dto
     */
    private NoticeDto mapToDto(Object obj) {
        Object[] re = (Object[]) obj;
        java.sql.Date sqlDate = (java.sql.Date) re[0];
        LocalDate date = sqlDate.toLocalDate();
        return NoticeDto.builder()
                .date(date)
                .title((String) re[1])
                .author((String) re[2])
                .url((String) re[3])
                .build();
    }
}