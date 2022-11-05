package com.foxminded.vitaliifedan.task10.services.impl;

import com.foxminded.vitaliifedan.task10.models.groups.Group;
import com.foxminded.vitaliifedan.task10.models.schedules.Course;
import com.foxminded.vitaliifedan.task10.models.schedules.Lecture;
import com.foxminded.vitaliifedan.task10.repositories.LectureRepository;
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

    private final LectureRepository lectureRepository;
    private final AudienceService audienceService;
    private final CourseService courseService;
    private final GroupService groupService;
    private final UserService userService;

    @Autowired
    public LectureServiceImpl(LectureRepository lectureRepository, AudienceService audienceService, CourseService courseService, GroupService groupService, UserService userService) {
        this.lectureRepository = lectureRepository;
        this.audienceService = audienceService;
        this.courseService = courseService;
        this.groupService = groupService;
        this.userService = userService;
    }

    @Transactional(readOnly = true)
    public List<Lecture> findAll() {
        return lectureRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Lecture> findById(Integer id) {
        return lectureRepository.findById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    public Lecture create(Lecture lecture) {
        return lectureRepository.save(lecture);
    }

    @Transactional
    public Lecture update(Lecture lecture) {
        return lectureRepository.save(lecture);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    public void deletedById(Integer id) {
        lectureRepository.deleteById(id);
    }

    @Transactional
    public Map<String, String> getFilledLecture(Integer id) {
        Optional<Lecture> lecture = lectureRepository.findById(id);
        Map<String, String> filledLecture = new HashMap<>();
        if (lecture.isPresent()) {
            if (lecture.get().getCourse() != null) {
                String courseName = courseService.findById(lecture.get().getCourse().getId())
                        .map(Course::getCourseName).orElse(null);
                filledLecture.put("course", courseName);
            }
            if (lecture.get().getTeacher() != null) {
                String teacherName = userService.findById(lecture.get().getTeacher().getId())
                        .map(teacher -> teacher.getName() + " " + teacher.getSurname()).orElse(null);
                filledLecture.put("teacher", teacherName);
            }
            if (lecture.get().getGroup() != null) {
                String groupName = groupService.findById(lecture.get().getGroup().getId())
                        .map(Group::getGroupName).orElse(null);
                filledLecture.put("group", groupName);
            }

            if (lecture.get().getAudience() != null) {
                String audience = audienceService.findById(lecture.get().getAudience().getId())
                        .map(a -> String.valueOf(a.getRoomNumber())).orElse(null);
                filledLecture.put("audience", audience);
            }

            filledLecture.put("lectureDate", lecture.get().getLectureDate().toString());
            filledLecture.put("pair", lecture.get().getPairNumber().toString());
            filledLecture.put("id", lecture.get().getId().toString());

        }
        return filledLecture;
    }
}
