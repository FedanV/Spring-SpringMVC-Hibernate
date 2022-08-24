package com.foxminded.vitaliifedan.task10.models.schedules;

import com.foxminded.vitaliifedan.task10.models.groups.Group;
import com.foxminded.vitaliifedan.task10.models.persons.Teacher;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Lecture {

    private String lectureName;
    private Audience audience;
    private Teacher teacher;
    private Date date;
    private Group group;
    private Days dayOfWeek;
    private int pairNumber;

    public Lecture() {
    }

    public Lecture(String lectureName, Audience audience, Teacher teacher, Date date, Group group, Days dayOfWeek, int pairNumber) {
        this.lectureName = lectureName;
        this.audience = audience;
        this.teacher = teacher;
        this.date = date;
        this.group = group;
        this.dayOfWeek = dayOfWeek;
        this.pairNumber = pairNumber;
    }
}
