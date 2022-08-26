package com.foxminded.vitaliifedan.task10.models.persons;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Student extends User {

    public Student(String name, String surname, String login, String password, Roles role, UserTypes userType) {
        super(name, surname, login, password, role, userType);
    }
}