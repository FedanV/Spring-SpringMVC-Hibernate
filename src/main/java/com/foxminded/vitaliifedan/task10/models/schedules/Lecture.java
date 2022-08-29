package com.foxminded.vitaliifedan.task10.models.schedules;

import com.foxminded.vitaliifedan.task10.models.groups.Group;
import com.foxminded.vitaliifedan.task10.models.persons.Teacher;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Lecture {


    int id;
    private Course course;
    private Teacher teacher;
    private LocalDate lectureDate;
    private Group group;
    private Integer pairNumber;
    private Audience audience;

    public Lecture() {
    }

    public Lecture(Course course, Teacher teacher, LocalDate lectureDate, Group group, Integer pairNumber, Audience audience) {
        this.course = course;
        this.audience = audience;
        this.teacher = teacher;
        this.lectureDate = lectureDate;
        this.group = group;
        this.pairNumber = pairNumber;
    }

    public Lecture(int id, Course course, Teacher teacher, LocalDate lectureDate, Group group, Integer pairNumber, Audience audience) {
        this.id = id;
        this.course = course;
        this.audience = audience;
        this.teacher = teacher;
        this.lectureDate = lectureDate;
        this.group = group;
        this.pairNumber = pairNumber;
    }
}
