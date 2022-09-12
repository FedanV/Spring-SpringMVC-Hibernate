package com.foxminded.vitaliifedan.task10.dao.impl;

import com.foxminded.vitaliifedan.task10.dao.AbstractCrudDao;
import com.foxminded.vitaliifedan.task10.dao.AudienceDao;
import com.foxminded.vitaliifedan.task10.exceptions.AudienceException;
import com.foxminded.vitaliifedan.task10.models.schedules.Audience;
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
public class AudienceDaoImpl extends AbstractCrudDao<Audience, Integer> implements AudienceDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AudienceDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    protected Audience create(Audience entity) {
        String createAudience = "INSERT INTO audience(room_number) VALUES(?)";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        int affectedRow = jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(createAudience, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, entity.getRoomNumber());
            return statement;
        }, keyHolder);
        if (affectedRow == 0) {
            throw new AudienceException("Audience " + entity.getRoomNumber() + " was not created");
        }
        int id = (int) keyHolder.getKeys().get("id");
        return new Audience(id, entity.getRoomNumber());
    }

    @Override
    protected Audience update(Audience entity) {
        String updateAudience = "UPDATE audience SET room_number=? WHERE id=?";
        int affectedRow = jdbcTemplate.update(updateAudience, entity.getRoomNumber(), entity.getId());
        if (affectedRow == 0) {
            throw new AudienceException("Audience " + entity.getRoomNumber() + " was not updated");
        }
        return new Audience(entity.getId(), entity.getRoomNumber());
    }

    @Override
    public Boolean delete(Integer id) {
        String deleteAudience = "DELETE FROM audience WHERE id=?";
        int affectedRow = jdbcTemplate.update(deleteAudience, id);
        if (affectedRow == 0) {
            throw new AudienceException("Audience with id " + id + " was not deleted");
        }
        return true;
    }

    @Override
    public List<Audience> getAll() {
        String getAllAudiences = "SELECT * FROM audience";
        return jdbcTemplate.query(getAllAudiences, new BeanPropertyRowMapper<>(Audience.class));
    }

    @Override
    public Optional<Audience> getById(Integer id) {
        String getAudienceById = "SELECT * FROM audience WHERE id=?";
        return jdbcTemplate.query(getAudienceById, new BeanPropertyRowMapper<>(Audience.class), id)
                .stream().findFirst();
    }
}
