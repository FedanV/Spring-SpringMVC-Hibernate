package com.foxminded.vitaliifedan.task10.dao;


import com.foxminded.vitaliifedan.task10.models.persons.TeacherCourse;

public interface TeacherCourseDao {

    TeacherCourse addCourseToTeacher(int teacherId, int courseId);

    Boolean removeCourseByTeacherId(int teacherId, int courseId);

    TeacherCourse updateCourseForTeacherId(int teacherId, int newCourseId, int oldCourseId);

}
