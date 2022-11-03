package com.foxminded.vitaliifedan.task10.dao.impl;

import com.foxminded.vitaliifedan.task10.dao.TeacherCourseDao;
import com.foxminded.vitaliifedan.task10.models.persons.Teacher;
import com.foxminded.vitaliifedan.task10.models.schedules.Course;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
public class TeacherCourseDaoImpl implements TeacherCourseDao {

    private static final Logger logger = LoggerFactory.getLogger(TeacherCourseDaoImpl.class);

    private final EntityManager entityManager;

    @Autowired
    public TeacherCourseDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Teacher addCourseToTeacher(int teacherId, int courseId) {
        logger.debug("Start adding course id={} to teacher id={}", courseId, teacherId);
        Teacher teacher = entityManager.find(Teacher.class, teacherId);
        Course course = entityManager.find(Course.class, courseId);
        teacher.setCourse(course);
        logger.debug("Finish adding course id={} to teacher id={}", courseId, teacherId);
        return teacher;
    }

    @Override
    public Boolean removeCourseByTeacherId(int teacherId, int courseId) {
        logger.debug("Start deletion course id={} for teacher id={}", courseId, teacherId);
        Teacher teacher = entityManager.createQuery("SELECT t FROM Teacher t WHERE t.id=:teacherId AND t.course.id=:courseId", Teacher.class)
                .setParameter("teacherId", teacherId)
                .setParameter("courseId", courseId)
                .getSingleResult();
        entityManager.remove(teacher);
        logger.debug("Finish deletion course id={} for teacher id={}", courseId, teacherId);
        return true;
    }

    @Override
    public Teacher updateCourseForTeacherId(int teacherId, int newCourseId, int oldCourseId) {
        logger.debug("Start updating course id={} for teacher id={}", oldCourseId, teacherId);
        Teacher teacher = entityManager.createQuery("SELECT t FROM Teacher t WHERE t.id=:teacherId AND t.course.id=:courseId", Teacher.class)
                .setParameter("teacherId", teacherId)
                .setParameter("courseId", oldCourseId)
                .getSingleResult();
        Course newCourse = entityManager.find(Course.class, newCourseId);
        teacher.setCourse(newCourse);
        logger.debug("Finish updating course id={} for teacher id={}", oldCourseId, teacherId);
        return teacher;
    }

}
