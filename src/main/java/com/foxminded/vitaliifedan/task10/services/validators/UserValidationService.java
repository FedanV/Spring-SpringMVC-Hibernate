package com.foxminded.vitaliifedan.task10.services.validators;

import com.foxminded.vitaliifedan.task10.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserValidationService {

    private final UserDao userDao;

    @Autowired
    public UserValidationService(UserDao userDao) {
        this.userDao = userDao;
    }

    public String validatePhoneNumber(String number) {
        String message = "";
        if (userDao.findUserByPhone(number).isPresent()) {
            message = "This phone number exists";
        }
        return message;
    }

    public String validateLogin(String login) {
        String message = "";
        if (userDao.findUserByLogin(login).isPresent()) {
            message = String.format("User with login: %s exist", login);
        }
        return message;
    }

}
