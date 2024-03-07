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

        long currentTimeMillis = System.currentTimeMillis();
        double score = currentTimeMillis + TimeUnit.SECONDS.toMillis(100);

        for (String token : departmentDto.getTokens()) {
            redisTemplate.opsForZSet().add(token,departmentDto.getDepartment(), score);
            redisTemplate.expire(token, 100, TimeUnit.SECONDS);
        }
    }
}
