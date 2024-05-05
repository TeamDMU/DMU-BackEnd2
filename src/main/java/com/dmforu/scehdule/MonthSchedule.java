package com.dmforu.scehdule;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MonthSchedule {
    private int month;
    private List<Schedule> scheduleEntries;
}
