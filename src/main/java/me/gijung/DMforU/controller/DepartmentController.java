package me.gijung.DMforU.controller;

import lombok.RequiredArgsConstructor;
import me.gijung.DMforU.model.dto.DepartmentDto;
import me.gijung.DMforU.model.dto.DepartmentRequestDto;
import me.gijung.DMforU.model.dto.TokenRequestDto;
import me.gijung.DMforU.service.DepartmentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/department/v1/dmu")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @PostMapping("/createDepartment")
    public void createDepartment(@RequestBody DepartmentRequestDto departmentRequestDto) {
        departmentService.createDepartment(
                new DepartmentDto(Arrays.asList(departmentRequestDto.getToken()), departmentRequestDto.getDepartment())
        );
    }

    @PostMapping("/updateDepartment")
    public void updateDepartment(@RequestBody DepartmentRequestDto departmentRequestDto) {
        departmentService.updateDepartment(
                new DepartmentDto(Arrays.asList(departmentRequestDto.getToken()), departmentRequestDto.getDepartment())
        );
    }

    @PostMapping("/deleteDepartment")
    public void deleteDepartment(@RequestBody TokenRequestDto tokenRequestDto) {
        departmentService.deleteDepartment(tokenRequestDto.getToken());
    }
}
