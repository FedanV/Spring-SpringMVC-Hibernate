package com.foxminded.vitaliifedan.task10.services.impl;

import com.foxminded.vitaliifedan.task10.dao.LectureDao;
import com.foxminded.vitaliifedan.task10.exceptions.LectureException;
import com.foxminded.vitaliifedan.task10.models.groups.Group;
import com.foxminded.vitaliifedan.task10.models.schedules.Course;
import com.foxminded.vitaliifedan.task10.models.schedules.Lecture;
import com.foxminded.vitaliifedan.task10.services.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class LectureServiceImpl implements LectureService {

    private static final Logger logger = LoggerFactory.getLogger(LectureServiceImpl.class);

    private final LectureDao lectureDao;
    private final AudienceService audienceService;
    private final CourseService courseService;
    private final GroupService groupService;
    private final UserService userService;

    @Autowired
    public LectureServiceImpl(LectureDao lectureDao, AudienceService audienceService, CourseService courseService, GroupService groupService, UserService userService) {
        this.lectureDao = lectureDao;
        this.audienceService = audienceService;
        this.courseService = courseService;
        this.groupService = groupService;
        this.userService = userService;
    }

    @Transactional(readOnly = true)
    public List<Lecture> findAll() {
        return lectureDao.getAll();
    }

    @Transactional(readOnly = true)
    public Optional<Lecture> findById(Integer id) {
        return lectureDao.getById(id);
    }
    @PreAuthorize("hasRole('ADMIN')")
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
    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    public Boolean deletedById(Integer id) {
        try {
            return lectureDao.delete(id);
        } catch (LectureException e) {
            logger.error("Exception happened when Lecture is deleting", e);
            throw e;
        }
    }

    @Transactional
    public Map<String, String> getFilledLecture(Integer id) {
        Optional<Lecture> lecture = lectureDao.getById(id);
        Map<String, String> filledLecture = new HashMap<>();
        if (lecture.isPresent()) {
            String courseName = courseService.findById(lecture.get().getCourse().getId())
                    .map(Course::getCourseName).orElse(null);

            String teacherName = userService.findById(lecture.get().getTeacher().getId())
                    .map(teacher -> teacher.getName() + " " + teacher.getSurname()).orElse(null);

            String groupName = groupService.findById(lecture.get().getGroup().getId())
                    .map(Group::getGroupName).orElse(null);

            String audience = audienceService.findById(lecture.get().getAudience().getId())
                    .map(a -> String.valueOf(a.getRoomNumber())).orElse(null);

            filledLecture.put("course", courseName);
            filledLecture.put("teacher", teacherName);
            filledLecture.put("lectureDate", lecture.get().getLectureDate().toString());
            filledLecture.put("group", groupName);
            filledLecture.put("pair", lecture.get().getPairNumber().toString());
            filledLecture.put("audience", audience);
            filledLecture.put("id", lecture.get().getId().toString());
        }
        return filledLecture;
    }
}
