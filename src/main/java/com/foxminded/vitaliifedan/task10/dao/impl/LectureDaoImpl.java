package com.foxminded.vitaliifedan.task10.dao.impl;

import com.foxminded.vitaliifedan.task10.dao.AbstractCrudDao;
import com.foxminded.vitaliifedan.task10.dao.LectureDao;
import com.foxminded.vitaliifedan.task10.models.schedules.Lecture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Component
public class LectureDaoImpl extends AbstractCrudDao<Lecture, Integer> implements LectureDao {

    private static final Logger logger = LoggerFactory.getLogger(LectureDaoImpl.class);

    private final EntityManager entityManager;

    @Autowired
    public LectureDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    protected Lecture create(Lecture entity) {
        logger.debug("Start creating Lecture name={}, date={}", entity.getCourse().getCourseName(), entity.getLectureDate());
        entityManager.persist(entity);
        logger.debug("Finish creating Lecture name={}, date={}", entity.getCourse().getCourseName(), entity.getLectureDate());
        return entity;
    }

    @Override
    protected Lecture update(Lecture entity) {
        logger.debug("Start updating Lecture name={}, date={}", entity.getCourse().getCourseName(), entity.getLectureDate());
        entityManager.merge(entity);
        logger.debug("Finish creating Lecture name={}, date={}", entity.getCourse().getCourseName(), entity.getLectureDate());
        return entity;
    }

    @Override
    public Boolean delete(Integer id) {
        logger.debug("Start deleting Lecture id={}", id);
        Lecture lecture = entityManager.find(Lecture.class, id);
        entityManager.remove(lecture);
        logger.debug("Finish deleting Lecture id={}", id);
        return true;
    }

    @Override
    public List<Lecture> getAll() {
        return entityManager.createQuery("SELECT l FROM Lecture l", Lecture.class).getResultList();
    }

    @Override
    public Optional<Lecture> getById(Integer id) {
        return Optional.ofNullable(entityManager.find(Lecture.class, id));
    }

}
