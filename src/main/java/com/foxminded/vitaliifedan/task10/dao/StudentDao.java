package com.foxminded.vitaliifedan.task10.dao;

public interface StudentDao {

    int addGroupToStudent(int userId, int groupId);
    int removeStudentFromGroup(int userId);
    int updateGroupForStudentId(int userId, int groupId);
}
