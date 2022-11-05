package com.foxminded.vitaliifedan.task10.services;

import com.foxminded.vitaliifedan.task10.models.persons.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll();

    Optional<User> findById(Integer id);

    User create(User user);

    User update(User user);

    void deletedById(Integer id);

    Optional<User> findUserByPhone(String phone);
    Optional<User> findUserByLogin(String login);

}
