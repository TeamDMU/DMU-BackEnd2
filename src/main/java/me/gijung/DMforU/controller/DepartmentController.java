package me.gijung.DMforU.controller;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import me.gijung.DMforU.model.dto.DepartmentDto;
import me.gijung.DMforU.service.RedisService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/department/v1/dmu")
@AllArgsConstructor
public class DepartmentController {

    private final RedisService redisService;

    /**
    * @param dto 학과,토큰
     */
    @PostMapping("/setting")
    public void get_Department(@RequestBody DepartmentDto dto) {
        redisService.updateDepartment(dto);
    }
}
