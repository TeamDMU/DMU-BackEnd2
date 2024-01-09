package me.gijung.DMforU.controller;

import lombok.RequiredArgsConstructor;
import me.gijung.DMforU.model.dto.SchedulerDto;
import me.gijung.DMforU.model.dto.DietDto;
import me.gijung.DMforU.service.DietService;
import me.gijung.DMforU.service.SchedulerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/dmu")
public class CrawlingController {

    private final DietService dietService;
    private final SchedulerService schedulerService;

    @GetMapping("/diet")
    public DietDto getDiet() {
        return dietService.getData();
    }

    @GetMapping("/scheduler")
    public SchedulerDto getScheduler() {
        return schedulerService.getData();
    }
}
