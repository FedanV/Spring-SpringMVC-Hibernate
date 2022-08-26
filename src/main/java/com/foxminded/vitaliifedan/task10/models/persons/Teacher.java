package com.foxminded.vitaliifedan.task10.models.persons;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Teacher extends User {

    private String specialization;

    public Teacher(String name, String surname, String login, String password, Role role, UserType userType) {
        super(name, surname, login, password, role, userType);
    }

}
