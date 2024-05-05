package com.dmforu.notice;

import com.dmforu.notice.dto.NoticeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/dmu/notice")
public class NoticeController {

    private final NoticeService noticeService;

    @GetMapping("/department")
    public ResponseEntity<List<NoticeRequest>> getDepartmentNotice(@RequestParam(name = "department") String department,
                                                               @RequestParam(name = "page", defaultValue = "1") int page,
                                                               @RequestParam(name = "size", defaultValue = "20") int size) {
        List<NoticeRequest> departmentNotices = noticeService.findDepartmentNotices(page, size, department);

        return ResponseEntity.ok().body(departmentNotices);
    }

    @GetMapping("/university")
    public ResponseEntity<List<NoticeRequest>> getUniversityNotice(
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "size", defaultValue = "20") int size) {
        List<NoticeRequest> universityNotices = noticeService.findUniversityNotices(page, size);

        return ResponseEntity.ok().body(universityNotices);
    }

    @GetMapping("/{searchWord}")
    public ResponseEntity<List<NoticeRequest>> getNoticeByKeyword(@PathVariable String searchWord,
                                                              @RequestParam(name = "department") String department,
                                                              @RequestParam(name = "page", defaultValue = "1") int page,
                                                              @RequestParam(name = "size", defaultValue = "20") int size) {
        List<NoticeRequest> notices = noticeService.getNotices(searchWord, department, page, size);

        return ResponseEntity.ok().body(notices);
    }
}
