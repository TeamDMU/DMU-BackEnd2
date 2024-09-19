package com.dmforu.cafeteria

import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDate

data class Diet(

        @Schema(description = "날짜", example = "2024-05-08")
        val date: LocalDate,

        @Schema(description = "메뉴", example = "[백미밥, 통살새우까스&타르타르, 불닭소스쌀떡볶이, 청포묵김가루무침, 배추김치, 연두부달걀탕]")
        val menus: List<String>
)