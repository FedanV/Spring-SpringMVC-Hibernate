package com.foxminded.vitaliifedan.task10.dao;

import com.foxminded.vitaliifedan.task10.models.persons.Teacher;


public interface TeacherDao {

    Teacher addCourseToTeacher(int teacherId, int courseId);

    Boolean removeCourseByTeacherId(int teacherId, int courseId);

    Teacher updateCourseForTeacherId(int teacherId, int newCourseId, int oldCourseId);
}
