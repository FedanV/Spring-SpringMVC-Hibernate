package com.foxminded.vitaliifedan.task10.dao.mappers;

import com.foxminded.vitaliifedan.task10.models.groups.Group;
import com.foxminded.vitaliifedan.task10.models.persons.User;
import com.foxminded.vitaliifedan.task10.models.schedules.Audience;
import com.foxminded.vitaliifedan.task10.models.schedules.Course;
import com.foxminded.vitaliifedan.task10.models.schedules.Lecture;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LectureRowMapper implements RowMapper<Lecture> {
    @Override
    public Lecture mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Lecture.builder()
                .id(rs.getInt("id"))
                .course(Course.builder().id(rs.getInt("course_id")).build())
                .teacher(User.builder().id(rs.getInt("teacher_id")).build())
                .lectureDate(rs.getDate("lecture_date").toLocalDate())
                .group(Group.builder().id(rs.getInt("group_id")).build())
                .pairNumber(rs.getInt("pair_number"))
                .audience(Audience.builder().id(rs.getInt("audience_id")).build())
                .build();
    }
}
