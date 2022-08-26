package com.foxminded.vitaliifedan.task10.models.persons;

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
    private Role role;
    private UserType userType;

    public User(String name, String surname, String login, String password, Role role, UserType userType) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.role = role;
        this.userType = userType;
    }
}
