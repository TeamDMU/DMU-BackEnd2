package com.dmforu.v2_messaging.util;

import com.dmforu.notice.Notice;
import com.dmforu.v2_messaging.dto.NoticeDtoV2;

public class mapToDTO {

    public static NoticeDtoV2 noticeToNoticeDTO(Notice notice) {
        return NoticeDtoV2.builder()
                .type(notice.getType())
                .date(notice.getDate())
                .title(notice.getTitle())
                .author(notice.getAuthor())
                .url(notice.getUrl())
                .build();
    }
}