package me.gijung.DMforU.utils;

import me.gijung.DMforU.model.dto.*;
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

    public static TypeNoticeDto maoToDepartmentNotice(Notice notice) {
        return TypeNoticeDto.builder()
                .type(notice.getType())
                .date(notice.getDate())
                .title(notice.getTitle())
                .author(notice.getAuthor())
                .url(notice.getUrl())
                .build();
    }

    //RequestTokenDto -> ServiceTokensDto
    // Token Update, Delete, Refresh 사용
    public static TokensDto RequestTokenDtoToServiceTokensDto(RequestTokenDto requestTokenDto) {
        return TokensDto.builder()
                .token(requestTokenDto.getToken())
                .topic(requestTokenDto.getTopic())
                .build();
    }

    //RequestInitDto -> ServiceTokensDto
    //초기 앱 진입시 Token 및 Topic 생성에 사용
    public static TokensDto RequestTokenDtoToServiceTokensDto(InitRequestDto requestInitDto) {
        return TokensDto.builder()
                .token(requestInitDto.getToken())
                .topic(requestInitDto.getTopic())
                .build();
    }

    //RequestInitDto -> ServiceDepartmentDto
    //초기 앱 진입시 학과 생성에 사용
    public static DepartmentDto RequestInitDtoToServiceDepartmentDto(InitRequestDto requestInitDto) {
        return DepartmentDto.builder()
                .token(requestInitDto.getToken())
                .department(requestInitDto.getDepartment())
                .build();
    }

}
