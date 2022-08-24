package com.foxminded.vitaliifedan.task10.models.persons;

import com.foxminded.vitaliifedan.task10.models.groups.Group;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {

    private String name;
    private String surname;
    private int age;
    private String phone;
    private String login;
    private String password;
    private Roles role;
    private UserTypes userType;
    private String specialization;
    private int course;
    private String faculty;
    private Group group;

    public User(String name, String surname, String login, String password, Roles role, UserTypes userType) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.role = role;
        this.userType = userType;
    }
}
