package com.foxminded.vitaliifedan.task10.mappers;

import com.foxminded.vitaliifedan.task10.models.persons.*;

public class UserMapper {

    private UserMapper() {
    }

    public static User createUser(String login, String password, Role role, UserType userType) {
        if ("TEACHER".equals(userType.toString())) {
            return new Teacher(login, password, role, userType);
        } else if ("STUDENT".equals(userType.toString())) {
            return new Student(login, password, role, userType);
        } else {
            return new User(login, password, role, userType);
        }
    }
}
