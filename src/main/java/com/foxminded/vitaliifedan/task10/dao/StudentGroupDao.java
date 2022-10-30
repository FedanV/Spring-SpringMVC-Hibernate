package com.foxminded.vitaliifedan.task10.dao;

import com.foxminded.vitaliifedan.task10.models.persons.StudentGroup;

public interface StudentGroupDao {

    StudentGroup addGroupToStudent(int userId, int groupId);

    Boolean removeStudentFromGroup(int userId);

    StudentGroup updateGroupForStudentId(int userId, int groupId);

}
