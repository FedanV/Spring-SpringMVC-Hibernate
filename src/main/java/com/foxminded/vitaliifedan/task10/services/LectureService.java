package com.foxminded.vitaliifedan.task10.services;

import com.foxminded.vitaliifedan.task10.dao.LectureDao;
import com.foxminded.vitaliifedan.task10.models.schedules.Lecture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class LectureService {

    private final LectureDao lectureDao;

    @Autowired
    public LectureService(LectureDao lectureDao) {
        this.lectureDao = lectureDao;
    }

    public List<Lecture> findAll() {
        return lectureDao.getAll();
    }

    public Optional<Lecture> findById(Integer id) {
        return lectureDao.getById(id);
    }

    @Transactional
    public Lecture create(Lecture lecture) {
        return lectureDao.save(lecture);
    }

    @Transactional
    public Lecture update(Lecture lecture) {
        return lectureDao.save(lecture);
    }

    @Transactional
    public Boolean deletedById(Integer id) {
        return lectureDao.delete(id);
    }
}
