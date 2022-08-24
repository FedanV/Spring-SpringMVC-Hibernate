package com.foxminded.vitaliifedan.task10.models.schedules;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class Schedule {

    private List<Lecture> lectures;

    public Schedule() {
    }

    public Schedule(List<Lecture> lectures) {
        this.lectures = lectures;
    }

}
