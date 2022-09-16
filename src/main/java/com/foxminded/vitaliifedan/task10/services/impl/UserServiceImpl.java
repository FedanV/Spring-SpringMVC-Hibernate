package com.foxminded.vitaliifedan.task10.services.impl;

import com.foxminded.vitaliifedan.task10.dao.StudentDao;
import com.foxminded.vitaliifedan.task10.dao.TeacherDao;
import com.foxminded.vitaliifedan.task10.dao.UserDao;
import com.foxminded.vitaliifedan.task10.exceptions.UserException;
import com.foxminded.vitaliifedan.task10.models.persons.Student;
import com.foxminded.vitaliifedan.task10.models.persons.Teacher;
import com.foxminded.vitaliifedan.task10.models.persons.User;
import com.foxminded.vitaliifedan.task10.models.persons.UserType;
import com.foxminded.vitaliifedan.task10.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserDao userDao;
    private final StudentDao studentDao;
    private final TeacherDao teacherDao;

    @Autowired
    public UserServiceImpl(UserDao userDao, StudentDao studentDao, TeacherDao teacherDao) {
        this.userDao = userDao;
        this.studentDao = studentDao;
        this.teacherDao = teacherDao;
    }

    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userDao.getAll();
    }

    @Transactional(readOnly = true)
    public Optional<User> findById(Integer id) {
        return userDao.getById(id);
    }

    @Transactional(readOnly = true)
    public List<User> getUserByUserType(UserType userType) {
        return userDao.getUsersByUserType(userType);
    }

    @Transactional
    public User create(User user) {
        try {
            return userDao.save(user);
        } catch (UserException e) {
            logger.error("Exception happened when User is creating", e);
            throw e;
        }
    }

    @Transactional
    public User update(User user) {
        try {
            return userDao.save(user);
        } catch (UserException e) {
            logger.error("Exception happened when User is updating", e);
            throw e;
        }
    }

    @Transactional
    public Boolean deletedById(Integer id) {
        try {
            return userDao.delete(id);
        } catch (UserException e) {
            logger.error("Exception happened when User is deleting", e);
            throw e;
        }
    }

    @Transactional
    public Student addGroupToStudent(Integer userId, Integer groupId) {
        try {
            return studentDao.addGroupToStudent(userId, groupId);
        } catch (UserException e) {
            logger.error("Exception happened when Group is adding to student", e);
            throw e;
        }
    }

    @Transactional
    public Boolean removeStudentFromGroup(Integer userId) {
        try {
            return studentDao.removeStudentFromGroup(userId);
        } catch (UserException e) {
            logger.error("Exception happened when removing Student from Group", e);
            throw e;
        }
    }

    @Transactional
    public Student updateGroupForStudent(Integer userId, Integer groupId) {
        try {
            return studentDao.updateGroupForStudentId(userId, groupId);
        } catch (UserException e) {
            logger.error("Exception happened when updating group", e);
            throw e;
        }
    }

    @Transactional
    public Teacher addCourseToTeacher(Integer teacherId, Integer courseId) {
        try {
            return teacherDao.addCourseToTeacher(teacherId, courseId);
        } catch (UserException e) {
            logger.error("Exception happened when adding Course to Teacher", e);
            throw e;
        }
    }

    @Transactional
    public Boolean removeCourseFromTeacher(Integer teacherId, Integer courseId) {
        try {
            return teacherDao.removeCourseByTeacherId(teacherId, courseId);
        } catch (UserException e) {
            logger.error("Exception happened when removing Teacher course", e);
            throw e;
        }
    }

    @Transactional
    public Teacher updateCourseForTeacher(Integer teacherId, Integer newCourseId, Integer oldCourseId) {
        try {
            return teacherDao.updateCourseForTeacherId(teacherId, newCourseId, oldCourseId);
        } catch (UserException e) {
            logger.error("Exception happened when updating Teacher course", e);
            throw e;
        }
    }
}
