package me.gijung.DMforU.service;

import lombok.RequiredArgsConstructor;
import me.gijung.DMforU.model.dto.DepartmentDto;
import me.gijung.DMforU.service.redis.Department;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final Department department;

    public void createDepartment(DepartmentDto departmentDto) {
        department.createDepartment(departmentDto);
    }

    public void updateDepartment(DepartmentDto departmentDto) {
        department.updateDepartment(departmentDto);
    }

    public void deleteDepartment(String token) {
        department.deleteDepartment(token);
    }
}
