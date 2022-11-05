package com.foxminded.vitaliifedan.task10.repositories;

import com.foxminded.vitaliifedan.task10.models.persons.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("update Teacher t set t.course.id = :courseId where t.id = :teacherId")
    void addCourseToTeacher(Integer courseId, Integer teacherId);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("update Teacher t set t.course.id = null where t.id = :teacherId")
    void removeCourseFromTeacher(Integer teacherId);
}
