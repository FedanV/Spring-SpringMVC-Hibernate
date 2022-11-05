package com.foxminded.vitaliifedan.task10.services.impl;

import com.foxminded.vitaliifedan.task10.models.persons.Teacher;
import com.foxminded.vitaliifedan.task10.repositories.TeacherRepository;
import com.foxminded.vitaliifedan.task10.services.TeacherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherServiceImpl implements TeacherService {

    private static final Logger logger = LoggerFactory.getLogger(TeacherServiceImpl.class);
    private final TeacherRepository teacherRepository;

    public TeacherServiceImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Teacher> findAll() {
        return teacherRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Teacher> findById(Integer id) {
        return teacherRepository.findById(id);
    }

    @Override
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public Teacher create(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @Override
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public Teacher update(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @Override
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public void deletedById(Integer id) {
        teacherRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void addCourseToTeacher(Integer courseId, Integer teacherId) {
        teacherRepository.addCourseToTeacher(courseId, teacherId);
    }

    @Override
    @Transactional
    public void removeCourseFromTeacher(Integer teacherId) {
        teacherRepository.removeCourseFromTeacher(teacherId);
    }

}
