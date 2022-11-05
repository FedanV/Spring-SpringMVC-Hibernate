package com.foxminded.vitaliifedan.task10.services.impl;

import com.foxminded.vitaliifedan.task10.models.persons.Student;
import com.foxminded.vitaliifedan.task10.repositories.StudentRepository;
import com.foxminded.vitaliifedan.task10.services.StudentService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Student> findById(Integer id) {
        return studentRepository.findById(id);
    }

    @Override
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public Student create(Student student) {
        return studentRepository.save(student);
    }

    @Override
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public Student update(Student student) {
        return studentRepository.save(student);
    }

    @Override
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public void deletedById(Integer id) {
        studentRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void addGroupToStudent(Integer groupId, Integer studentId) {
        studentRepository.addGroupToStudent(groupId, studentId);
    }

    @Override
    @Transactional
    public void removeStudentFromGroup(Integer studentId) {
        studentRepository.removeStudentFromGroup(studentId);
    }


}
