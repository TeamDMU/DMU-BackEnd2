package com.dmforu.scehdule;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Schedule {

    private String[] date;
    private String content;

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Month {
        private int month;
        private List<Schedule> monthSchedule;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Year {
        private int year;
        private List<Month> yearSchedule;
    }
}
