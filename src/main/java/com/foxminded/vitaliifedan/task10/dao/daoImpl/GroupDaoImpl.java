package com.foxminded.vitaliifedan.task10.dao.daoImpl;

import com.foxminded.vitaliifedan.task10.dao.GroupDao;
import com.foxminded.vitaliifedan.task10.models.groups.Group;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class GroupDaoImpl implements GroupDao {

    private JdbcTemplate jdbcTemplate;

    public GroupDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Integer create(Group entity) {
        String createGroup = "INSERT INTO groups(group_name) VALUES(?)";
        return jdbcTemplate.update(createGroup, entity.getGroupName());
    }

    @Override
    public Integer update(Group entity) {
        String updateGroup = "UPDATE groups SET group_name=? WHERE id=?";
        return jdbcTemplate.update(updateGroup, entity.getGroupName(), entity.getId());
    }

    @Override
    public Integer delete(Integer id) {
        String deleteGroup = "DELETE FROM groups WHERE id=?";
        return jdbcTemplate.update(deleteGroup, id);
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
