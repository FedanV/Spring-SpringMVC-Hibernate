package com.foxminded.vitaliifedan.task10.dao.rowMappers;

import com.foxminded.vitaliifedan.task10.models.groups.Group;
import com.foxminded.vitaliifedan.task10.models.persons.Teacher;
import com.foxminded.vitaliifedan.task10.models.schedules.Audience;
import com.foxminded.vitaliifedan.task10.models.schedules.Course;
import com.foxminded.vitaliifedan.task10.models.schedules.Lecture;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LectureRowMapper implements RowMapper<Lecture> {
    @Override
    public Lecture mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Lecture(
                rs.getInt("id"),
                new Course(rs.getInt("course_id")),
                new Teacher(rs.getInt("teacher_id")),
                rs.getDate("lecture_date").toLocalDate(),
                new Group(rs.getInt("group_id")),
                rs.getInt("pair_number"),
                new Audience(rs.getInt("audience_id"), null)
        );
    }
}
