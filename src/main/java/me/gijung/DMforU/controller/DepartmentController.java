package me.gijung.DMforU.controller;

import lombok.RequiredArgsConstructor;
import me.gijung.DMforU.model.dto.ServiceDepartmentDto;
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

    @PostMapping("/updateDepartment")
    public void updateDepartment(@RequestBody ServiceDepartmentDto departmentDto) {
        departmentService.updateDepartment(departmentDto);
    }

    @PostMapping("/deleteDepartment")
    public void deleteDepartment(@RequestBody ServiceDepartmentDto departmentDto) {
        departmentService.deleteDepartment(departmentDto);
    }
}
