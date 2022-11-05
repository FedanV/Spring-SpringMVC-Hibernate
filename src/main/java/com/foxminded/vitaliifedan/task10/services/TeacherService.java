package com.foxminded.vitaliifedan.task10.services;

import com.foxminded.vitaliifedan.task10.models.persons.Teacher;

import java.util.List;
import java.util.Optional;

public interface TeacherService {

    List<Teacher> findAll();

    Optional<Teacher> findById(Integer id);

    Teacher create(Teacher teacher);

    Teacher update(Teacher teacher);

    void deletedById(Integer id);

    void addCourseToTeacher(Integer courseId, Integer teacherId);
    void removeCourseFromTeacher(Integer teacherId);

}
