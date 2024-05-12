package me.gijung.DMforU.model.domain.schedule;

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
