package com.foxminded.vitaliifedan.task10.models.persons;

import com.foxminded.vitaliifedan.task10.models.groups.Group;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Student extends User {

    private Group group;

    public Student(Integer id, String name, String surname, String phone, String login, String password, Role role, UserType userType) {
        super(id, name, surname, phone, login, password, role, userType);
    }

    public Student(String name, String surname, String phone, String login, String password, Role role, UserType userType) {
        super(name, surname, phone, login, password, role, userType);
    }

    public Student(Integer id, Group group) {
        super(id);
        this.group = group;
    }

    public Student() {
    }
}
