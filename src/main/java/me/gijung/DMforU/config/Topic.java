package me.gijung.DMforU.config;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Topic {
    menu("학식", "menu"),
    schedule("일정", "menu");

    private final String koreanName;
    private final String englishName;
}
