package com.foxminded.vitaliifedan.task10.models.schedules;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Course {

    private int id;
    private String courseName;

    public Course() {
    }

    public Course(String courseName) {
        this.courseName = courseName;
    }

    public Course(int id, String courseName) {
        this.id = id;
        this.courseName = courseName;
    }

    public Course(int id) {
        this.id = id;
    }
}
