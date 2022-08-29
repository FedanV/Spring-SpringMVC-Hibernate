package com.foxminded.vitaliifedan.task10.dao.daoImpl;

import com.foxminded.vitaliifedan.task10.dao.LectureDao;
import com.foxminded.vitaliifedan.task10.dao.rowMappers.LectureRowMapper;
import com.foxminded.vitaliifedan.task10.models.schedules.Lecture;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;

public class LectureDaoImpl implements LectureDao {

    private final JdbcTemplate jdbcTemplate;

    public LectureDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Integer create(Lecture entity) {
        String createLecture = "INSERT INTO lecture(course_id, teacher_id, lecture_date, group_id, pair_number, audience_id)" +
                "VALUES(?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(createLecture,
                entity.getCourse().getId(),
                entity.getTeacher().getId(),
                entity.getLectureDate(),
                entity.getGroup().getId(),
                entity.getPairNumber(),
                entity.getAudience().getId()
        );
    }

    @Override
    public Integer update(Lecture entity) {
        String updateLecture = "UPDATE lecture SET course_id=?, teacher_id=?, lecture_date=?, " +
                "group_id=?, pair_number=?, audience_id=? WHERE id=?";
        return jdbcTemplate.update(updateLecture, entity.getCourse().getId(), entity.getTeacher().getId(),
                entity.getLectureDate(),
                entity.getGroup().getId(),
                entity.getPairNumber(),
                entity.getAudience().getId(),
                entity.getId()
        );
    }

    @Override
    public Integer delete(Integer id) {
        String deleteLecture = "DELETE FROM lecture WHERE id=?";
        return jdbcTemplate.update(deleteLecture, id);
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
