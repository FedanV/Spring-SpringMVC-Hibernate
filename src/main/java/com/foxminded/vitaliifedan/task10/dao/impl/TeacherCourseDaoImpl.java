package com.foxminded.vitaliifedan.task10.dao.impl;

import com.foxminded.vitaliifedan.task10.dao.TeacherCourseDao;
import com.foxminded.vitaliifedan.task10.models.persons.TeacherCourse;
import com.foxminded.vitaliifedan.task10.models.persons.User;
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
    public TeacherCourse addCourseToTeacher(int teacherId, int courseId) {
        logger.debug("Start adding course id={} to teacher id={}", courseId, teacherId);
        User teacher = entityManager.find(User.class, teacherId);
        Course course = entityManager.find(Course.class, courseId);
        TeacherCourse teacherCourse = new TeacherCourse();
        teacherCourse.setUser(teacher);
        teacherCourse.setCourse(course);
        entityManager.persist(teacherCourse);
        logger.debug("Finish adding course id={} to teacher id={}", courseId, teacherId);
        return teacherCourse;
    }

    @Override
    public Boolean removeCourseByTeacherId(int teacherId, int courseId) {
        logger.debug("Start deletion course id={} for teacher id={}", courseId, teacherId);
        TeacherCourse teacherCourse = entityManager.createQuery("SELECT tc FROM TeacherCourse tc WHERE tc.user.id=:teacherId AND tc.course.id=:courseId", TeacherCourse.class)
                .setParameter("teacherId", teacherId)
                .setParameter("courseId", courseId)
                .getSingleResult();
        entityManager.remove(teacherCourse);
        logger.debug("Finish deletion course id={} for teacher id={}", courseId, teacherId);
        return true;
    }

    @Override
    public TeacherCourse updateCourseForTeacherId(int teacherId, int newCourseId, int oldCourseId) {
        logger.debug("Start updating course id={} for teacher id={}", oldCourseId, teacherId);
        TeacherCourse teacherCourse = entityManager.createQuery("SELECT tc FROM TeacherCourse tc WHERE tc.user.id=:teacherId AND tc.course.id=:courseId", TeacherCourse.class)
                .setParameter("teacherId", teacherId)
                .setParameter("courseId", oldCourseId)
                .getSingleResult();
        Course newCourse = entityManager.find(Course.class, newCourseId);
        teacherCourse.setCourse(newCourse);
        logger.debug("Finish updating course id={} for teacher id={}", oldCourseId, teacherId);
        return teacherCourse;
    }

}
