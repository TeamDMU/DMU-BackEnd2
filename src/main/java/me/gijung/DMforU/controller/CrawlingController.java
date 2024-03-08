package me.gijung.DMforU.controller;

import lombok.RequiredArgsConstructor;
import me.gijung.DMforU.model.domain.WeeklyMenu;
import me.gijung.DMforU.model.domain.schedule.YearSchedule;
import me.gijung.DMforU.model.dto.NoticeDto;
import me.gijung.DMforU.service.DepartmentNoticeService;
import me.gijung.DMforU.service.MenuService;
import me.gijung.DMforU.service.NoticeService;
import me.gijung.DMforU.service.ScheduleService;
import me.gijung.DMforU.service.UniversityNoticeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/dmu")
public class CrawlingController {

    private final MenuService menuService;
    private final ScheduleService scheduleService;
    private final DepartmentNoticeService departmentNoticeService;
    private final UniversityNoticeService universityNoticeService;
    private final NoticeService noticeService;

    @GetMapping("/cafeteria")
    public ResponseEntity<List<WeeklyMenu>> getDiet() {
        return ResponseEntity.ok().body(menuService.getData());
    }

    @GetMapping("/schedule")
    public ResponseEntity<List<YearSchedule>> getScheduler() {
        return ResponseEntity.ok().body(scheduleService.getData());
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
