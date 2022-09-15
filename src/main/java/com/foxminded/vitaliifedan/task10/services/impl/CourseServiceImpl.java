package com.foxminded.vitaliifedan.task10.services.impl;

import com.foxminded.vitaliifedan.task10.dao.CourseDao;
import com.foxminded.vitaliifedan.task10.exceptions.CourseException;
import com.foxminded.vitaliifedan.task10.models.schedules.Course;
import com.foxminded.vitaliifedan.task10.services.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    private static final Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);

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
        try {
            return courseDao.save(course);
        } catch (CourseException e) {
            logger.error("Exception happened when Course is creating", e);
        }
        return null;
    }

    @Transactional
    public Course update(Course course) {
        try {
            return courseDao.save(course);
        } catch (CourseException e) {
            logger.error("Exception happened when Course is updating", e);
        }
        return null;
    }

    @Transactional
    public Boolean deleteById(Integer id) {
        try {
            return courseDao.delete(id);
        } catch (CourseException e) {
            logger.error("Exception happened when Course is deleting", e);
        }
        return false;
    }
}
