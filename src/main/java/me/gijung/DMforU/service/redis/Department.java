package me.gijung.DMforU.service.redis;

import lombok.RequiredArgsConstructor;
import me.gijung.DMforU.model.dto.DepartmentDto;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class Department {

    private final RedisTemplate<String, String> redisTemplate;

    public void update_department(DepartmentDto departmentDto) {

        for (String token : departmentDto.getTokens()) {
            redisTemplate.opsForZSet().add(token,departmentDto.getDepartment(), 100);
//            redisTemplate.expire(token, 100, TimeUnit.SECONDS);
        }
    }

    public void delete_department(DepartmentDto departmentDto) {
        for (String token : departmentDto.getTokens()) {
            redisTemplate.opsForZSet().remove(token,departmentDto.getDepartment());
        }
    }
}
