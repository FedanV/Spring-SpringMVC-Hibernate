package com.foxminded.vitaliifedan.task10.dao.daoImpl;

import com.foxminded.vitaliifedan.task10.dao.AbstractCrudDao;
import com.foxminded.vitaliifedan.task10.dao.GroupDao;
import com.foxminded.vitaliifedan.task10.exceptions.GroupException;
import com.foxminded.vitaliifedan.task10.models.groups.Group;
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
public class GroupDaoImpl extends AbstractCrudDao<Group, Integer> implements GroupDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public GroupDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    protected Group create(Group entity) {
        String createGroup = "INSERT INTO groups(group_name) VALUES(?)";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        int affectedRow = jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(createGroup, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, entity.getGroupName());
            return statement;
        }, keyHolder);
        if (affectedRow == 0) {
            throw new GroupException("Group " + entity.getGroupName() + " was not created");
        }
        int id = (int) keyHolder.getKeys().get("id");
        return new Group(id, entity.getGroupName());
    }

    @Override
    protected Group update(Group entity) {
        String updateGroup = "UPDATE groups SET group_name=? WHERE id=?";
        int affectedRow = jdbcTemplate.update(updateGroup, entity.getGroupName(), entity.getId());
        if (affectedRow == 0) {
            throw new GroupException("Group " + entity.getGroupName() + " was not updated");
        }
        return new Group(entity.getId(), entity.getGroupName());
    }

    @Override
    public Boolean delete(Integer id) {
        String deleteGroup = "DELETE FROM groups WHERE id=?";
        int affectedRow = jdbcTemplate.update(deleteGroup, id);
        if (affectedRow == 0) {
            throw new GroupException("Group with id " + id + " was not deleted");
        }
        return true;
    }

    @Override
    public List<Group> getAll() {
        String getAllGroups = "SELECT * FROM groups";
        return jdbcTemplate.query(getAllGroups, new BeanPropertyRowMapper<>(Group.class));
    }

    @Override
    public Optional<Group> getById(Integer id) {
        String getGroupById = "SELECT * FROM groups WHERE id=?";
        return jdbcTemplate.query(getGroupById, new BeanPropertyRowMapper<>(Group.class), id)
                .stream().findFirst();
    }
}
