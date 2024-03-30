package me.gijung.DMforU.service.redis;

import lombok.RequiredArgsConstructor;
import me.gijung.DMforU.model.dto.DepartmentDto;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Department {

    private final RedisTemplate<String, String> redisTemplate;


    public void createDepartment(DepartmentDto departmentDto) {
            redisTemplate.opsForZSet().add(departmentDto.getToken(),departmentDto.getDepartment(), -2);
    }


    public void updateDepartment(DepartmentDto departmentDto) {
            redisTemplate.opsForZSet().removeRange(departmentDto.getToken(), 0, 0);
            redisTemplate.opsForZSet().add(departmentDto.getToken(),departmentDto.getDepartment(), -2);
    }

    public void deleteDepartment(DepartmentDto departmentDto) {
            redisTemplate.opsForZSet().remove(departmentDto.getToken(),departmentDto.getDepartment());
    }
}
