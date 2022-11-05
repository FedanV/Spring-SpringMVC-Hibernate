package com.foxminded.vitaliifedan.task10.repositories;

import com.foxminded.vitaliifedan.task10.models.schedules.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

    Optional<Course> findCourseByCourseName(String courseName);

}
