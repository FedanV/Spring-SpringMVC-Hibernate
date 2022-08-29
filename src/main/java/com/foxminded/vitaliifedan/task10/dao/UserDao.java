package com.foxminded.vitaliifedan.task10.dao;

import com.foxminded.vitaliifedan.task10.models.persons.User;
import com.foxminded.vitaliifedan.task10.models.persons.UserType;

import java.util.List;

public interface UserDao extends CrudDao<User, Integer> {

    List<User> getUsersByUserType(UserType userType);
}
