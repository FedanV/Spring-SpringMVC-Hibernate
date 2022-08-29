package com.foxminded.vitaliifedan.task10.dao.daoImpl;

import com.foxminded.vitaliifedan.task10.dao.AudienceDao;
import com.foxminded.vitaliifedan.task10.models.schedules.Audience;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;

public class AudienceDaoImpl implements AudienceDao {

    private final JdbcTemplate jdbcTemplate;

    public AudienceDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Integer create(Audience entity) {
        String createAudience = "INSERT INTO audience(room_number) VALUES(?)";
        return jdbcTemplate.update(createAudience, entity.getRoomNumber());
    }

    @Override
    public Integer update(Audience entity) {
        String updateAudience = "UPDATE audience SET room_number=? WHERE id=?";
        return jdbcTemplate.update(updateAudience, entity.getRoomNumber(), entity.getId());
    }

    @Override
    public Integer delete(Integer id) {
        String deleteAudience = "DELETE FROM audience WHERE id=?";
        return jdbcTemplate.update(deleteAudience, id);
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
