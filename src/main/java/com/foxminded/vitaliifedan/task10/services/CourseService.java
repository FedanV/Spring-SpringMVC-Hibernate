package com.foxminded.vitaliifedan.task10.services;

import com.foxminded.vitaliifedan.task10.models.schedules.Course;

import java.util.List;
import java.util.Optional;

public interface CourseService {
    List<Course> findAll();

    Optional<Course> findById(Integer id);

    Course create(Course course);

    Course update(Course course);

    Boolean deleteById(Integer id);
}
