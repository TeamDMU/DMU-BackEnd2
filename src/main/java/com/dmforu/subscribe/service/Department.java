package com.dmforu.subscribe.service;

import com.dmforu.subscribe.dto.DepartmentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Department {

    private final static String DEFAULT_DEPARTMENT = " DEFAULT";
    private final RedisTemplate<String, String> redisTemplate;

    public void createDepartment(DepartmentDto departmentDto) {
        redisTemplate.opsForZSet().add(departmentDto.getToken(),departmentDto.getDepartment(), -2);
    }

    public void updateDepartment(DepartmentDto departmentDto) {
        redisTemplate.opsForZSet().removeRange(departmentDto.getToken(), 0, 0);
        redisTemplate.opsForZSet().add(departmentDto.getToken(),departmentDto.getDepartment(), -2);
    }

    public void deleteDepartment(String token) {
        redisTemplate.opsForZSet().removeRange(token, 0, 0);
        redisTemplate.opsForZSet().add(token,DEFAULT_DEPARTMENT, -2);
    }
}