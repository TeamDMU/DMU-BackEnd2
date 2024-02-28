package me.gijung.DMforU.controller;

import lombok.RequiredArgsConstructor;
import me.gijung.DMforU.model.domain.Diet;
import me.gijung.DMforU.model.domain.schedule.YearSchedule;
import me.gijung.DMforU.model.dto.NoticeDto;
import me.gijung.DMforU.service.DepartmentNoticeService;
import me.gijung.DMforU.service.DietService;
import me.gijung.DMforU.service.NoticeService;
import me.gijung.DMforU.service.SchedulerService;
import me.gijung.DMforU.service.UniversityNoticeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/dmu")
public class CrawlingController {

    private final DietService dietService;
    private final SchedulerService schedulerService;
    private final DepartmentNoticeService departmentNoticeService;
    private final UniversityNoticeService universityNoticeService;
    private final NoticeService noticeService;

    @GetMapping("/cafeteria")
    public ResponseEntity<List<Diet>> getDiet() {
        return ResponseEntity.ok().body(dietService.getData());
    }

    @GetMapping("/schedule")
    public ResponseEntity<List<YearSchedule>> getScheduler() {
        return ResponseEntity.ok().body(schedulerService.getData());
    }

    @GetMapping("/departmentNotice/{department}")
    public List<NoticeDto> getDepartmentNotice(@PathVariable String department,
                                               @RequestParam(name = "page", defaultValue = "1") int page,
                                               @RequestParam(name = "size", defaultValue = "20") int size) {
        List<NoticeDto> departmentNotices = departmentNoticeService.findDepartmentNotices(page, size, department);

        return departmentNotices;
    }

    @GetMapping("/universityNotice")
    public List<NoticeDto> getUniversityNotice(@RequestParam(name = "page", defaultValue = "1") int page,
                                               @RequestParam(name = "size", defaultValue = "20") int size) {
        List<NoticeDto> universityNotices = universityNoticeService.findUniversityNotices(page, size);

        return universityNotices;
    }

    @GetMapping("/notice/{searchWord}")
    public List<NoticeDto> getNoticeByKeyword(@PathVariable String searchWord,
                                              @RequestParam(name = "department") String department,
                                              @RequestParam(name = "page", defaultValue = "1") int page,
                                              @RequestParam(name = "size", defaultValue = "20") int size) {
        List<NoticeDto> notices = noticeService.getNotices(searchWord, department, page, size);

        return notices;
    }
}
