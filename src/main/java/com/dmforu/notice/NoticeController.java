package com.dmforu.notice;

import com.dmforu.notice.dto.NoticeRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name="공지")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notice")
public class NoticeController {

    private final NoticeService noticeService;

    @Operation(summary = "학과 공지 API", description = "해당하는 학과의 공지를 출력한다.<br>Page, Size의 default 값은 1, 20이다.")
    @GetMapping("/department/v1")
    public ResponseEntity<List<NoticeRequest>> getDepartmentNotice(@RequestParam(name = "department") String department,
                                                                   @RequestParam(name = "page", defaultValue = "1") int page,
                                                                   @RequestParam(name = "size", defaultValue = "20") int size) {
        List<NoticeRequest> departmentNotices = noticeService.findDepartmentNotices(page, size, department);

        return ResponseEntity.ok().body(departmentNotices);
    }

    @Operation(summary = "대학 공지 API", description = "대학 공지를 출력한다.<br>Page, Size의 default 값은 1, 20이다.")
    @GetMapping("/university/v1")
    public ResponseEntity<List<NoticeRequest>> getUniversityNotice(
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "size", defaultValue = "20") int size) {
        List<NoticeRequest> universityNotices = noticeService.findUniversityNotices(page, size);

        return ResponseEntity.ok().body(universityNotices);
    }

    @Operation(summary = "공지 검색 API", description = "해당하는 학과와 대학 공지에서 해당하는 공지를 출력한다.<br>Page, Size의 default 값은 1, 20이다.")
    @GetMapping("/{searchWord}/v1")
    public ResponseEntity<List<NoticeRequest>> getNoticeByKeyword(@PathVariable String searchWord,
                                                                  @RequestParam(name = "department") String department,
                                                                  @RequestParam(name = "page", defaultValue = "1") int page,
                                                                  @RequestParam(name = "size", defaultValue = "20") int size) {
        List<NoticeRequest> notices = noticeService.getNotices(searchWord, department, page, size);

        return ResponseEntity.ok().body(notices);
    }
}
