package com.foxminded.vitaliifedan.task10.services;

import com.foxminded.vitaliifedan.task10.models.persons.Student;
import com.foxminded.vitaliifedan.task10.models.persons.Teacher;
import com.foxminded.vitaliifedan.task10.models.persons.User;
import com.foxminded.vitaliifedan.task10.models.persons.UserType;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll();

    Optional<User> findById(Integer id);

    List<User> getUserByUserType(UserType userType);

    User create(User user);

    User update(User user);

    Boolean deletedById(Integer id);

    Student addGroupToStudent(Integer userId, Integer groupId);

    Boolean removeStudentFromGroup(Integer userId);

    Student updateGroupForStudent(Integer userId, Integer groupId);

    Teacher addCourseToTeacher(Integer teacherId, Integer courseId);

    Boolean removeCourseFromTeacher(Integer teacherId, Integer courseId);

    Teacher updateCourseForTeacher(Integer teacherId, Integer newCourseId, Integer oldCourseId);
}
