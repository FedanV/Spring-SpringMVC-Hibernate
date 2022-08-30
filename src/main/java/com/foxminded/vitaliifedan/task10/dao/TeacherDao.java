package com.foxminded.vitaliifedan.task10.dao;

import com.foxminded.vitaliifedan.task10.models.persons.Teacher;

import java.sql.SQLException;

public interface TeacherDao {

    Teacher addCourseToTeacher(int teacherId, int courseId) throws SQLException;
    Boolean removeCourseByTeacherId(int teacherId, int courseId) throws SQLException;
    Teacher updateCourseForTeacherId(int teacherId, int newCourseId, int oldCourseId) throws SQLException;
}
