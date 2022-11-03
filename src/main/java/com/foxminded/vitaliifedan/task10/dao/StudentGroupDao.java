package com.foxminded.vitaliifedan.task10.dao;

import com.foxminded.vitaliifedan.task10.models.persons.Student;

public interface StudentGroupDao {

    Student addGroupToStudent(int userId, int groupId);

    Boolean removeStudentFromGroup(int userId);

    Student updateGroupForStudentId(int userId, int groupId);

}
