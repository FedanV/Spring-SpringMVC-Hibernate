package com.foxminded.vitaliifedan.task10.repositories;

import com.foxminded.vitaliifedan.task10.models.persons.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findUserByLogin(String login);

    Optional<User> findUserByPhone(String phone);

}
