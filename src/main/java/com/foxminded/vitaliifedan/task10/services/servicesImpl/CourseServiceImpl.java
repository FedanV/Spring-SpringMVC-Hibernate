package com.foxminded.vitaliifedan.task10.services.servicesImpl;

import com.foxminded.vitaliifedan.task10.dao.CourseDao;
import com.foxminded.vitaliifedan.task10.models.schedules.Course;
import com.foxminded.vitaliifedan.task10.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseDao courseDao;

    @Autowired
    public CourseServiceImpl(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    @Transactional(readOnly = true)
    public List<Course> findAll() {
        return courseDao.getAll();
    }

    @Transactional(readOnly = true)
    public Optional<Course> findById(Integer id) {
        return courseDao.getById(id);
    }

    @Transactional
    public Course create(Course course) {
        return courseDao.save(course);
    }

    @Transactional
    public Course update(Course course) {
        return courseDao.save(course);
    }

    @Transactional
    public Boolean deleteById(Integer id) {
        return courseDao.delete(id);
    }
}
