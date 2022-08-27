package com.foxminded.vitaliifedan.task10.models.persons;

import com.foxminded.vitaliifedan.task10.models.groups.Group;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Student extends User {

    private Group group;

    public Student(String login, String password, Role role, UserType userType) {
        super(login, password, role, userType);
    }
}
