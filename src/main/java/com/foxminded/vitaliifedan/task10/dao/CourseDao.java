package com.foxminded.vitaliifedan.task10.dao;

import com.foxminded.vitaliifedan.task10.models.schedules.Course;

import java.util.Optional;

public interface CourseDao extends CrudDao<Course, Integer>{

    Optional<Course> findByCourseName(String name);
}
