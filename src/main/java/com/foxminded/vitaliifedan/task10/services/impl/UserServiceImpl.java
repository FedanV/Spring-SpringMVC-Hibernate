package com.foxminded.vitaliifedan.task10.services.impl;

import com.foxminded.vitaliifedan.task10.dao.StudentGroupDao;
import com.foxminded.vitaliifedan.task10.dao.TeacherCourseDao;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserDao userDao;
    private final StudentGroupDao studentGroupDao;
    private final TeacherCourseDao teacherCourseDao;

    @Autowired
    public UserServiceImpl(UserDao userDao, StudentGroupDao studentGroupDao, TeacherCourseDao teacherCourseDao) {
        this.userDao = userDao;
        this.studentGroupDao = studentGroupDao;
        this.teacherCourseDao = teacherCourseDao;
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

    @PreAuthorize("hasRole('ADMIN')")
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
            return studentGroupDao.addGroupToStudent(userId, groupId);
        } catch (UserException e) {
            logger.error("Exception happened when Group is adding to student", e);
            throw e;
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    public Boolean removeStudentFromGroup(Integer userId) {
        try {
            return studentGroupDao.removeStudentFromGroup(userId);
        } catch (UserException e) {
            logger.error("Exception happened when removing Student from Group", e);
            throw e;
        }
    }

    @Transactional
    public Student updateGroupForStudent(Integer userId, Integer groupId) {
        try {
            return studentGroupDao.updateGroupForStudentId(userId, groupId);
        } catch (UserException e) {
            logger.error("Exception happened when updating group", e);
            throw e;
        }
    }

    @Transactional
    public Teacher addCourseToTeacher(Integer teacherId, Integer courseId) {
        try {
            return teacherCourseDao.addCourseToTeacher(teacherId, courseId);
        } catch (UserException e) {
            logger.error("Exception happened when adding Course to Teacher", e);
            throw e;
        }
    }

    @Transactional
    public Boolean removeCourseFromTeacher(Integer teacherId, Integer courseId) {
        try {
            return teacherCourseDao.removeCourseByTeacherId(teacherId, courseId);
        } catch (UserException e) {
            logger.error("Exception happened when removing Teacher course", e);
            throw e;
        }
    }

    @Transactional
    public Teacher updateCourseForTeacher(Integer teacherId, Integer newCourseId, Integer oldCourseId) {
        try {
            return teacherCourseDao.updateCourseForTeacherId(teacherId, newCourseId, oldCourseId);
        } catch (UserException e) {
            logger.error("Exception happened when updating Teacher course", e);
            throw e;
        }
    }

    @Override
    public Optional<User> findUserByPhone(String phone) {
        return userDao.findUserByPhone(phone);
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return userDao.findUserByLogin(login)
                .map(user -> new org.springframework.security.core.userdetails.User(
                        user.getLogin(),
                        user.getPassword(),
                        Collections.singleton(user.getRole())
                ))
                .orElseThrow(() -> new UsernameNotFoundException("Failed to retrieve user: " + login));
    }


}
