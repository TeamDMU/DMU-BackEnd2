package com.dmforu.schedule;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class YearSchedule {
    private int year;
    private List<MonthSchedule> yearSchedule;
}
