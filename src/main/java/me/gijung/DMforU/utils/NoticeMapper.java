package me.gijung.DMforU.utils;

import me.gijung.DMforU.model.dto.NoticeDto;
import me.gijung.DMforU.model.entity.Notice;

public class NoticeMapper {

    /**
     * 공지사항 엔티티를 dto로 변환한다.
     *
     * @param notice 공지사항 엔티티
     * @return 공지사항 dto
     */
    public static NoticeDto mapToDto(Notice notice) {
        return NoticeDto.builder()
                .date(notice.getDate())
                .title(notice.getTitle())
                .author(notice.getAuthor())
                .url(notice.getUrl())
                .build();
    }
}
