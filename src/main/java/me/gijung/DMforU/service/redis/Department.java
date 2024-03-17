package me.gijung.DMforU.service.redis;

import lombok.RequiredArgsConstructor;
import me.gijung.DMforU.config.Major;
import me.gijung.DMforU.config.Topic;
import me.gijung.DMforU.model.dto.DepartmentDto;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Department {

    private final RedisTemplate<String, String> redisTemplate;

    public void updateDepartment(DepartmentDto departmentDto) {
        for (String token : departmentDto.getTokens()) {
            redisTemplate.opsForZSet().add(token,departmentDto.getDepartment(), -2);
//            redisTemplate.expire(token, 100, TimeUnit.SECONDS);
        }
    }

    public void deleteDepartment(DepartmentDto departmentDto) {
        for (String token : departmentDto.getTokens()) {
            redisTemplate.opsForZSet().remove(token,departmentDto.getDepartment());
        }
    }
}
