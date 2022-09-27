package com.foxminded.vitaliifedan.task10.models.persons;

import com.foxminded.vitaliifedan.task10.models.schedules.Course;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Teacher extends User {

    private List<Course> courses;

    public Teacher(Integer id) {
        super(id);
    }

    public Teacher(Integer id, String name, String surname, String phone, String login, String password, Role role, UserType userType) {
        super(id, name, surname, phone, login, password, role, userType);
    }

    public Teacher(String name, String surname, String phone, String login, String password, Role role, UserType userType) {
        super(name, surname, phone, login, password, role, userType);
    }

    public Teacher(Integer id, List<Course> courses) {
        super(id);
        this.courses = courses;
    }

    public Teacher() {

    }

}
