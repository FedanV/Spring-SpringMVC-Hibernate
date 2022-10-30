package com.foxminded.vitaliifedan.task10.dao.impl;

import com.foxminded.vitaliifedan.task10.dao.AbstractCrudDao;
import com.foxminded.vitaliifedan.task10.dao.CourseDao;
import com.foxminded.vitaliifedan.task10.models.schedules.Course;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

@Component
public class CourseDaoImpl extends AbstractCrudDao<Course, Integer> implements CourseDao {

    private static final Logger logger = LoggerFactory.getLogger(CourseDaoImpl.class);

    private final EntityManager entityManager;

    @Autowired
    public CourseDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    protected Course create(Course entity) {
        logger.debug("Start creating Course {}", entity.getCourseName());
        entityManager.persist(entity);
        logger.debug("Finish creating Course {}", entity.getCourseName());
        return entity;
    }

    @Override
    protected Course update(Course entity) {
        logger.debug("Start updating Course {}", entity.getCourseName());
        entityManager.merge(entity);
        logger.debug("Finish updating Course {}", entity.getCourseName());
        return entity;
    }

    @Override
    public Boolean delete(Integer id) {
        logger.debug("Start deleting Course id={}", id);
        Course course = entityManager.find(Course.class, id);
        entityManager.remove(course);
        logger.debug("Finish deleting Course id={}", id);
        return true;
    }

    @Override
    public List<Course> getAll() {
        return entityManager.createQuery("SELECT c FROM Course c", Course.class).getResultList();
    }

    @Override
    public Optional<Course> getById(Integer id) {
        return Optional.ofNullable(entityManager.find(Course.class, id));
    }

    @Override
    public Optional<Course> findByCourseName(String name) {
        try {
            return Optional.ofNullable(entityManager.createQuery("SELECT c FROM Course c WHERE c.courseName=:courseName", Course.class)
                    .setParameter("courseName", name)
                    .getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
}
