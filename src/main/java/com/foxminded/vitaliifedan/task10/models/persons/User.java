package com.foxminded.vitaliifedan.task10.models.persons;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {

    int id;
    private String name;
    private String surname;
    private Integer age;
    private String phone;
    private String login;
    private String password;
    private Role role;
    private UserType userType;

    public User() {
    }

    public User(String login, String password, Role role, UserType userType) {
        this.login = login;
        this.password = password;
        this.role = role;
        this.userType = userType;
    }

    public User(int id, String login, String password, Role role, UserType userType) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.role = role;
        this.userType = userType;
    }

    public User(int id) {
        this.id = id;
    }
}
