package me.gijung.DMforU.controller;

import lombok.RequiredArgsConstructor;
import me.gijung.DMforU.model.domain.Diet;
import me.gijung.DMforU.model.domain.schedule.YearSchedule;
import me.gijung.DMforU.service.DietService;
import me.gijung.DMforU.service.SchedulerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/dmu")
public class CrawlingController {

    private final DietService dietService;
    private final SchedulerService schedulerService;

    @GetMapping("/diet")
    public List<Diet> getDiet() {
        return dietService.getData();
    }

    @GetMapping("/scheduler")
    public List<YearSchedule> getScheduler() {
        return schedulerService.getData();
    }
}
