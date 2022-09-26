package com.foxminded.vitaliifedan.task10.models.schedules;

import com.foxminded.vitaliifedan.task10.models.IntegerId;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
public class Course extends IntegerId {

    @NotEmpty(message = "Name can't be empty")
    @Size(min = 3, max = 30, message = "Course should contains 3 to 30 symbols")
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
