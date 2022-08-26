package com.foxminded.vitaliifedan.task10.models.persons;

import com.foxminded.vitaliifedan.task10.models.groups.Group;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Student extends User {

    private int course;
    private String faculty;
    private Group group;

    public Student(String name, String surname, String login, String password, Role role, UserType userType) {
        super(name, surname, login, password, role, userType);
    }
}
