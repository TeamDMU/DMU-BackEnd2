package com.dmforu.cafeteria;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name="[구버전] 식단")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/dmu/cafeteria")
public class LegacyCafeteriaController {

    private final CafeteriaService cafeteriaService;

    @GetMapping
    public ResponseEntity<List<Diet>> getDiet() {
        return ResponseEntity.ok().body(cafeteriaService.getData());
    }
}
