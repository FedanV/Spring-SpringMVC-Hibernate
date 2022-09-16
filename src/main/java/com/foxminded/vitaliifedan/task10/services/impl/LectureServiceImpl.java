package com.foxminded.vitaliifedan.task10.services.impl;

import com.foxminded.vitaliifedan.task10.dao.LectureDao;
import com.foxminded.vitaliifedan.task10.exceptions.LectureException;
import com.foxminded.vitaliifedan.task10.models.schedules.Lecture;
import com.foxminded.vitaliifedan.task10.services.LectureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class LectureServiceImpl implements LectureService {

    private static final Logger logger = LoggerFactory.getLogger(LectureServiceImpl.class);

    private final LectureDao lectureDao;

    @Autowired
    public LectureServiceImpl(LectureDao lectureDao) {
        this.lectureDao = lectureDao;
    }

    @Transactional(readOnly = true)
    public List<Lecture> findAll() {
        return lectureDao.getAll();
    }

    @Transactional(readOnly = true)
    public Optional<Lecture> findById(Integer id) {
        return lectureDao.getById(id);
    }

    @Transactional
    public Lecture create(Lecture lecture) {
        try {
            return lectureDao.save(lecture);
        } catch (LectureException e) {
            logger.error("Exception happened when Lecture is creating", e);
            throw e;
        }
    }

    @Transactional
    public Lecture update(Lecture lecture) {
        try {
            return lectureDao.save(lecture);
        } catch (LectureException e) {
            logger.error("Exception happened when Lecture is updating", e);
            throw e;
        }
    }

    @Transactional
    public Boolean deletedById(Integer id) {
        try {
            return lectureDao.delete(id);
        } catch (LectureException e) {
            logger.error("Exception happened when Lecture is deleting", e);
            throw e;
        }
    }
}
