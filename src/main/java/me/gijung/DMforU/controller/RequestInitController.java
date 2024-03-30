package me.gijung.DMforU.controller;

import lombok.AllArgsConstructor;
import me.gijung.DMforU.model.dto.RequestInitDto;
import me.gijung.DMforU.service.InitService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/token/v1/dmu")
public class RequestInitController {

    private final InitService initService;

    @PostMapping("/createInit")
    public void createTokenDepartment(@RequestBody RequestInitDto requestInitDto) {
        initService.createTokenDepartment(requestInitDto);
    }

}
