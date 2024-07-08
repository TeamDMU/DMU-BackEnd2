package com.dmforu.subscribe.dtoV2;

import lombok.Data;

@Data
public class DepartmentDTO {

    private String token;
    private String department;

    public  DepartmentDTO(){}

    public DepartmentDTO(String token, String department) {
        this.token = token;
        this.department = department;
    }
}
