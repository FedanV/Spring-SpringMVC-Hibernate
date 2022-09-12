package com.foxminded.vitaliifedan.task10.dao;

import com.foxminded.vitaliifedan.task10.models.persons.Student;


public interface StudentDao {

    Student addGroupToStudent(int userId, int groupId);

    Boolean removeStudentFromGroup(int userId);

    Student updateGroupForStudentId(int userId, int groupId);
}
