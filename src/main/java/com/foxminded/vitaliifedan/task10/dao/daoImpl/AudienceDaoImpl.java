package com.foxminded.vitaliifedan.task10.dao.daoImpl;

import com.foxminded.vitaliifedan.task10.dao.AbstractCrudDao;
import com.foxminded.vitaliifedan.task10.dao.AudienceDao;
import com.foxminded.vitaliifedan.task10.models.schedules.Audience;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public class AudienceDaoImpl extends AbstractCrudDao<Audience, Integer> implements AudienceDao {

    private final JdbcTemplate jdbcTemplate;

    public AudienceDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    protected Audience create(Audience entity) throws SQLException {
        String createAudience = "INSERT INTO audience(room_number) VALUES(?)";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        int affectedRow = jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(createAudience, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, entity.getRoomNumber());
            return statement;
        }, keyHolder);
        if (affectedRow == 0) {
            throw new SQLException("Audience " + entity.getRoomNumber() + " was not created");
        }
        int id = (int) keyHolder.getKeys().get("id");
        return new Audience(id, entity.getRoomNumber());
    }

    @Override
    protected Audience update(Audience entity) throws SQLException {
        String updateAudience = "UPDATE audience SET room_number=? WHERE id=?";
        int affectedRow = jdbcTemplate.update(updateAudience, entity.getRoomNumber(), entity.getId());
        if (affectedRow == 0) {
            throw new SQLException("Audience " + entity.getRoomNumber() + " was not updated");
        }
        return new Audience(entity.getId(), entity.getRoomNumber());
    }

    @Override
    public Boolean delete(Integer id) throws SQLException {
        String deleteAudience = "DELETE FROM audience WHERE id=?";
        int affectedRow = jdbcTemplate.update(deleteAudience, id);
        if (affectedRow == 0) {
            throw new SQLException("Audience with id " + id + " was not deleted");
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
