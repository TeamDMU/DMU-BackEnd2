package me.gijung.DMforU.model.dto;


import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class DepartmentDto {
    private List<String> tokens;
    private String department;
}
