package com.foxminded.vitaliifedan.task10.services;

import com.foxminded.vitaliifedan.task10.models.persons.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    List<Student> findAll();

    Optional<Student> findById(Integer id);

    Student create(Student student);

    Student update(Student student);

    void deletedById(Integer id);

    void addGroupToStudent(Integer groupId, Integer studentId);

    void removeStudentFromGroup(Integer studentId);
}
