package com.foxminded.vitaliifedan.task10.dao;

public interface TeacherDao {

    int addCourseToTeacher(int teacherId, int courseId);
    int removeCourseByTeacherId(int teacherId, int courseId);
    int updateCourseForTeacherId(int teacherId, int newCourseId, int oldCourseId);
}
