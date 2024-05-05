package com.dmforu.cafeteria;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/dmu/cafeteria")
public class CafeteriaController {

    private final CafeteriaService cafeteriaService;

    @GetMapping
    public ResponseEntity<List<WeeklyMenu>> getDiet() {
        return ResponseEntity.ok().body(cafeteriaService.getData());
    }
}
