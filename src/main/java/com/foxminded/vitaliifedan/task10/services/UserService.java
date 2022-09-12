package com.foxminded.vitaliifedan.task10.services;

import com.foxminded.vitaliifedan.task10.dao.StudentDao;
import com.foxminded.vitaliifedan.task10.dao.TeacherDao;
import com.foxminded.vitaliifedan.task10.dao.UserDao;
import com.foxminded.vitaliifedan.task10.models.persons.Student;
import com.foxminded.vitaliifedan.task10.models.persons.Teacher;
import com.foxminded.vitaliifedan.task10.models.persons.User;
import com.foxminded.vitaliifedan.task10.models.persons.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserService {

    private final UserDao userDao;
    private final StudentDao studentDao;
    private final TeacherDao teacherDao;

    @Autowired
    public UserService(UserDao userDao, StudentDao studentDao, TeacherDao teacherDao) {
        this.userDao = userDao;
        this.studentDao = studentDao;
        this.teacherDao = teacherDao;
    }

    public List<User> findAll() {
        return userDao.getAll();
    }

    public Optional<User> findById(Integer id) {
        return userDao.getById(id);
    }

    public List<User> getUserByUserType(UserType userType) {
        return userDao.getUsersByUserType(userType);
    }

    @Transactional
    public User create(User user) {
        return userDao.save(user);
    }

    @Transactional
    public User update(User user) {
        return userDao.save(user);
    }

    @Transactional
    public Boolean deletedById(Integer id) {
        return userDao.delete(id);
    }

    @Transactional
    public Student addGroupToStudent(Integer userId, Integer groupdId) {
        return studentDao.addGroupToStudent(userId, groupdId);
    }

    @Transactional
    public Boolean removeStudentFromGroup(Integer userId) {
        return studentDao.removeStudentFromGroup(userId);
    }

    @Transactional
    public Student updateGroupForStudent(Integer userId, Integer groupId) {
        return studentDao.updateGroupForStudentId(userId, groupId);
    }

    @Transactional
    public Teacher addCourseToTeacher(Integer teacherId, Integer courseId) {
        return teacherDao.addCourseToTeacher(teacherId, courseId);
    }

    @Transactional
    public Boolean removeCourseFromTeacher(Integer teacherId, Integer courseId) {
        return teacherDao.removeCourseByTeacherId(teacherId, courseId);
    }

    @Transactional
    public Teacher updateCourseForTeacher(Integer teacherId, Integer newCourseId, Integer oldCourseId) {
        return teacherDao.updateCourseForTeacherId(teacherId, newCourseId, oldCourseId);
    }
}
