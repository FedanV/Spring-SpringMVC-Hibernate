package com.foxminded.vitaliifedan.task10.dao.daoImpl;

import com.foxminded.vitaliifedan.task10.dao.AbstractCrudDao;
import com.foxminded.vitaliifedan.task10.dao.LectureDao;
import com.foxminded.vitaliifedan.task10.dao.rowMappers.LectureRowMapper;
import com.foxminded.vitaliifedan.task10.models.schedules.Lecture;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public class LectureDaoImpl extends AbstractCrudDao<Lecture, Integer> implements LectureDao {

    private final JdbcTemplate jdbcTemplate;

    public LectureDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    protected Lecture create(Lecture entity) throws SQLException {
        String createLecture = "INSERT INTO lecture(course_id, teacher_id, lecture_date, group_id, pair_number, audience_id)" +
                "VALUES(?, ?, ?, ?, ?, ?)";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        int affectedRow = jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(createLecture, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, entity.getCourse().getId());
            statement.setInt(2, entity.getTeacher().getId());
            statement.setDate(3, Date.valueOf(entity.getLectureDate()));
            statement.setInt(4, entity.getGroup().getId());
            statement.setInt(5, entity.getPairNumber());
            statement.setInt(6, entity.getAudience().getId());
            return statement;
        }, keyHolder);
        if (affectedRow == 0) {
            throw new SQLException("Lecture " + entity.getCourse().getCourseName() + ", "
                    + entity.getLectureDate().toString() + " was not created");
        }
        int id = (int) keyHolder.getKeys().get("id");
        return new Lecture(id, entity.getCourse(), entity.getTeacher(), entity.getLectureDate(),
                entity.getGroup(), entity.getPairNumber(), entity.getAudience());
    }

    @Override
    protected Lecture update(Lecture entity) throws SQLException {
        String updateLecture = "UPDATE lecture SET course_id=?, teacher_id=?, lecture_date=?, " +
                "group_id=?, pair_number=?, audience_id=? WHERE id=?";
        int affectedRow = jdbcTemplate.update(updateLecture, entity.getCourse().getId(), entity.getTeacher().getId(),
                entity.getLectureDate(), entity.getGroup().getId(), entity.getPairNumber(), entity.getAudience().getId(),
                entity.getId());
        if (affectedRow == 0) {
            throw new SQLException("Lecture " + entity.getCourse().getCourseName() + ", "
                    + entity.getLectureDate().toString() + " was not updated");
        }
        return new Lecture(entity.getId(), entity.getCourse(), entity.getTeacher(), entity.getLectureDate(),
                entity.getGroup(), entity.getPairNumber(), entity.getAudience());
    }

    @Override
    public Boolean delete(Integer id) throws SQLException {
        String deleteLecture = "DELETE FROM lecture WHERE id=?";
        int affectedRow = jdbcTemplate.update(deleteLecture, id);
        if (affectedRow == 0) {
            throw new SQLException("Lecture with id " + id + " was not deleted");
        }
        return true;
    }

    @Override
    public List<Lecture> getAll() {
        String getAllLecture = "SELECT * FROM lecture";
        return jdbcTemplate.query(getAllLecture, new LectureRowMapper());
    }


    @Override
    public Optional<Lecture> getById(Integer id) {
        String getLectureById = "SELECT * FROM lecture WHERE id=?";
        return jdbcTemplate.query(getLectureById, new BeanPropertyRowMapper<>(Lecture.class), id)
                .stream().findFirst();
    }

}
