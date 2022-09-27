package com.foxminded.vitaliifedan.task10.models.persons;

import com.foxminded.vitaliifedan.task10.models.IntegerId;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
public class User extends IntegerId {
    @NotEmpty(message = "Name should not be empty!")
    @Size(min = 3, max = 30, message = "Name should contain 3 to 30 characters")
    private String name;
    @NotEmpty(message = "Surname should not be empty!")
    @Size(min = 3, max = 30, message = "Surname should contain 3 to 30 characters")
    private String surname;
    @NotEmpty(message = "Phone should not be empty!")
    @Size(min = 6, max = 14, message = "Phone should contain 6 to 14 digits")
    private String phone;
    @NotEmpty(message = "Login should not be empty!")
    private String login;
    @NotEmpty(message = "Password should not be empty!")
    private String password;
    private Role role;
    private UserType userType;


    public User(Integer id) {
        super(id);
    }

    public User(Integer id, String name, String surname, String phone, String login, String password, Role role, UserType userType) {
        super(id);
        this.login = login;
        this.password = password;
        this.role = role;
        this.userType = userType;
        this.phone = phone;
        this.name = name;
        this.surname = surname;
    }

    public User() {
    }

    public User(String name, String surname, String phone, String login, String password, Role role, UserType userType) {
        this.login = login;
        this.password = password;
        this.role = role;
        this.userType = userType;
        this.phone = phone;
        this.name = name;
        this.surname = surname;
    }
}
