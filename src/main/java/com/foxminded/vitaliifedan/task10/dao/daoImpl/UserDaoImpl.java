package com.foxminded.vitaliifedan.task10.dao.daoImpl;

import com.foxminded.vitaliifedan.task10.dao.UserDao;
import com.foxminded.vitaliifedan.task10.models.persons.User;
import com.foxminded.vitaliifedan.task10.models.persons.UserType;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {

    private final JdbcTemplate jdbcTemplate;

    public UserDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Integer create(User entity) {
        String createUser = "INSERT INTO users(login, password, role, user_type) VALUES(?, ?, ?, ?)";
        return jdbcTemplate.update(createUser, entity.getLogin(), entity.getPassword(),
                entity.getRole().toString(), entity.getUserType().toString());
    }

    @Override
    public Integer update(User entity) {
        String updateUser = "UPDATE users SET login=?, password=?, role=?, user_type=? WHERE id=?";
        return jdbcTemplate.update(updateUser, entity.getLogin(), entity.getPassword(),
                entity.getRole().toString(), entity.getUserType().toString(), entity.getId());
    }

    @Override
    public Integer delete(Integer id) {
        String deleteUser = "DELETE FROM users WHERE id=?";
        return jdbcTemplate.update(deleteUser, id);
    }

    @Override
    public List<User> getAll() {
        String getAllUsers = "SELECT * FROM users";
        return jdbcTemplate.query(getAllUsers, new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public Optional<User> getById(Integer id) {
        String getUserById = "SELECT * FROM users WHERE id=?";
        return jdbcTemplate.query(getUserById, new BeanPropertyRowMapper<>(User.class), id)
                .stream().findFirst();
    }

    @Override
    public List<User> getUsersByUserType(UserType userType) {
        String getUsersByUserType = "SELECT * FROM users WHERE user_type=?";
        return jdbcTemplate.query(getUsersByUserType, new BeanPropertyRowMapper<>(User.class), userType.toString());
    }

}
