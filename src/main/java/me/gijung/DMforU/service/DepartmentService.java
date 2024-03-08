package me.gijung.DMforU.service;

import lombok.RequiredArgsConstructor;
import me.gijung.DMforU.model.dto.DepartmentDto;
import me.gijung.DMforU.service.redis.Department;
import me.gijung.DMforU.service.redis.Token;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DepartmentService {
    private final Department department;

    public void update_department(DepartmentDto departmentDto) {
        department.update_department(departmentDto);
    }
}