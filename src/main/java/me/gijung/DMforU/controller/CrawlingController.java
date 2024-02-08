package me.gijung.DMforU.controller;

import lombok.RequiredArgsConstructor;
import me.gijung.DMforU.model.domain.Diet;
import me.gijung.DMforU.model.domain.schedule.YearSchedule;
import me.gijung.DMforU.model.dto.DepartmentNoticeDto;
import me.gijung.DMforU.service.DepartmentNoticeService;
import me.gijung.DMforU.service.DietService;
import me.gijung.DMforU.service.SchedulerService;
import org.springframework.data.domain.Page;
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

    @GetMapping("/diet")
    public List<Diet> getDiet() {
        return dietService.getData();
    }

    @GetMapping("/scheduler")
    public List<YearSchedule> getScheduler() {
        return schedulerService.getData();
    }

    @GetMapping("/departmentNotice/{department}")
    public List<DepartmentNoticeDto> getDepartmentNotice(@PathVariable String department,
                                                         @RequestParam(name = "page", defaultValue = "1") int page,
                                                         @RequestParam(name = "size", defaultValue = "20") int size) {
        List<DepartmentNoticeDto> pageDepartmentNotices = departmentNoticeService.findDepartmentNotices(page, size, department);

        return pageDepartmentNotices;
    }
}
