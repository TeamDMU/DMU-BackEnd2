package com.dmforu.subscribe.dtoV2;

import lombok.Data;

@Data
public class DepartmentStatusDTO {

    private String token;
    private String department;
    private boolean departmentOnOFF;

}