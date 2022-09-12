package com.foxminded.vitaliifedan.task10.dao.daoImpl;

import com.foxminded.vitaliifedan.task10.dao.AbstractCrudDao;
import com.foxminded.vitaliifedan.task10.dao.UserDao;
import com.foxminded.vitaliifedan.task10.exceptions.UserException;
import com.foxminded.vitaliifedan.task10.models.persons.User;
import com.foxminded.vitaliifedan.task10.models.persons.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Component
public class UserDaoImpl extends AbstractCrudDao<User, Integer> implements UserDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    protected User create(User entity) {
        String createUser = "INSERT INTO users(login, password, role, user_type) VALUES(?, ?, ?, ?)";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        int affectedRow = jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(createUser, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, entity.getLogin());
            statement.setString(2, entity.getPassword());
            statement.setString(3, entity.getRole().toString());
            statement.setString(4, entity.getUserType().toString());
            return statement;
        }, keyHolder);
        if (affectedRow == 0) {
            throw new UserException("User with login " + entity.getLogin() + " was not created");
        }
        int id = (int) keyHolder.getKeys().get("id");
        return new User(id, entity.getLogin(), entity.getPassword(), entity.getRole(), entity.getUserType());
    }

    @Override
    protected User update(User entity) {
        String updateUser = "UPDATE users SET login=?, password=?, role=?, user_type=? WHERE id=?";
        int affectedRow = jdbcTemplate.update(updateUser, entity.getLogin(), entity.getPassword(),
                entity.getRole().toString(), entity.getUserType().toString(), entity.getId());
        if (affectedRow == 0) {
            throw new UserException("User with login " + entity.getLogin() + " was not updated");
        }
        return new User(entity.getId(), entity.getLogin(), entity.getPassword(), entity.getRole(), entity.getUserType());
    }

    @Override
    public Boolean delete(Integer id) {
        String deleteUser = "DELETE FROM users WHERE id=?";
        int affectedRow = jdbcTemplate.update(deleteUser, id);
        if (affectedRow == 0) {
            throw new UserException("User with id " + id + " was not deleted");
        }
        return true;
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
