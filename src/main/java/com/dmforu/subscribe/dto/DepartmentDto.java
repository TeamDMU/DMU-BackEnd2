package com.dmforu.subscribe.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class DepartmentDto {
    private String token;
    private String department;
}
