package com.foxminded.vitaliifedan.task10.models.schedules;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class SemesterTimetable {
    private List<Schedule> scheduleList;

    public SemesterTimetable() {
    }

    public SemesterTimetable(List<Schedule> scheduleList) {
        this.scheduleList = scheduleList;
    }
}
