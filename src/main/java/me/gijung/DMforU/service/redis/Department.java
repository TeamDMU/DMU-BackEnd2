package me.gijung.DMforU.service.redis;

import lombok.RequiredArgsConstructor;
import me.gijung.DMforU.model.dto.ServiceDepartmentDto;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Department {

    private final RedisTemplate<String, String> redisTemplate;


    public void createDepartment(ServiceDepartmentDto departmentDto) {
            redisTemplate.opsForZSet().add(departmentDto.getTokens(),departmentDto.getDepartment(), -2);
    }


    public void updateDepartment(ServiceDepartmentDto departmentDto) {
            redisTemplate.opsForZSet().removeRange(departmentDto.getTokens(), 0, 0);
            redisTemplate.opsForZSet().add(departmentDto.getTokens(),departmentDto.getDepartment(), -2);
    }

    public void deleteDepartment(ServiceDepartmentDto departmentDto) {
            redisTemplate.opsForZSet().remove(departmentDto.getTokens(),departmentDto.getDepartment());
    }
}
