package me.gijung.DMforU.controller;

import lombok.RequiredArgsConstructor;
import me.gijung.DMforU.model.dto.DepartmentDto;
import me.gijung.DMforU.service.DepartmentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/department/v1/dmu")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @PostMapping("/setting")
    public void update_department(@RequestBody DepartmentDto departmentDto) {
        departmentService.update_department(departmentDto);
    }
}
