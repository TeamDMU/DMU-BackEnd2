package me.gijung.DMforU.service;

import lombok.RequiredArgsConstructor;
import me.gijung.DMforU.model.dto.ServiceDepartmentDto;
import me.gijung.DMforU.service.redis.Department;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final Department department;


    public void createDepartment(ServiceDepartmentDto departmentDto) {
        department.createDepartment(departmentDto);
    }

    public void updateDepartment(ServiceDepartmentDto departmentDto) {
        department.updateDepartment(departmentDto);
    }

    public void deleteDepartment(ServiceDepartmentDto departmentDto) {
        department.deleteDepartment(departmentDto);
    }
}
