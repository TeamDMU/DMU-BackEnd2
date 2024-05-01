package me.gijung.DMforU.model.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
@AllArgsConstructor
public class DepartmentDto {
    private List<String> tokens;
    private String department;

}
