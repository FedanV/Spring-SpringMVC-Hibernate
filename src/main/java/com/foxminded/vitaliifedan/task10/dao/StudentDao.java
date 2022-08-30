package com.foxminded.vitaliifedan.task10.dao;

import com.foxminded.vitaliifedan.task10.models.persons.Student;

import java.sql.SQLException;

public interface StudentDao {

    Student addGroupToStudent(int userId, int groupId) throws SQLException;

    Boolean removeStudentFromGroup(int userId) throws SQLException;

    Student updateGroupForStudentId(int userId, int groupId) throws SQLException;
}
