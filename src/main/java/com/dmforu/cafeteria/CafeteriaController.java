package com.dmforu.cafeteria;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name="식단")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cafeteria")
public class CafeteriaController {

    private final CafeteriaService cafeteriaService;

    @Operation(summary = "식단표 API", description = "금주의 식단을 출력한다.<br>식단표는 매주 일요일에 갱신된다.<br>만약, 공휴일인 경우는 빈 리스트를 출력한다.")
    @GetMapping("/v1")
    public ResponseEntity<List<Diet>> getDiet() {
        return ResponseEntity.ok().body(cafeteriaService.getData());
    }
}
