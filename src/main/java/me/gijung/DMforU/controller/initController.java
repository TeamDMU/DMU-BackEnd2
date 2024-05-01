package me.gijung.DMforU.controller;

import lombok.RequiredArgsConstructor;
import me.gijung.DMforU.model.dto.DepartmentDto;
import me.gijung.DMforU.model.dto.InitRequestDto;
import me.gijung.DMforU.model.dto.TokensDto;
import me.gijung.DMforU.service.DepartmentService;
import me.gijung.DMforU.service.TokenService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequiredArgsConstructor
@RequestMapping("/token/v1/dmu")
public class initController {

    private final TokenService tokenService;
    private final DepartmentService departmentService;

    @PostMapping("/initToken")
    public void createTopic(@RequestBody InitRequestDto initRequestDto){

        tokenService.createToken(
                new TokensDto(Arrays.asList(initRequestDto.getToken()), initRequestDto.getTopics())
        );
        departmentService.createDepartment(
                new DepartmentDto(Arrays.asList(initRequestDto.getToken()), initRequestDto.getDepartment())
        );
    }
}
