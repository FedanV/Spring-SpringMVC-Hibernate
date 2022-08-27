package com.foxminded.vitaliifedan.task10.models.schedules;

import com.foxminded.vitaliifedan.task10.models.groups.Group;
import com.foxminded.vitaliifedan.task10.models.persons.Teacher;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Lecture {


    int id;
    private Course course;
    private Audience audience;
    private Teacher teacher;
    private Date lectureDate;
    private Group group;
    private int pairNumber;

    public Lecture() {
    }

    public Lecture(Course course, Audience audience, Teacher teacher, Date lectureDate, Group group, int pairNumber) {
        this.course = course;
        this.audience = audience;
        this.teacher = teacher;
        this.lectureDate = lectureDate;
        this.group = group;
        this.pairNumber = pairNumber;
    }
}
