package com.foxminded.vitaliifedan.task10.services.validators;

import com.foxminded.vitaliifedan.task10.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserValidationService {

    private final UserService userService;

    @Autowired
    public UserValidationService(UserService userService) {
        this.userService = userService;
    }

    public String validatePhoneNumber(String number) {
        String message = "";
        if (userService.findUserByPhone(number).isPresent()) {
            message = "This phone number exists";
        }
        return message;
    }

    public String validateLogin(String login) {
        String message = "";
        if (userService.findUserByLogin(login).isPresent()) {
            message = String.format("User with login: %s exist", login);
        }
        return message;
    }

}
