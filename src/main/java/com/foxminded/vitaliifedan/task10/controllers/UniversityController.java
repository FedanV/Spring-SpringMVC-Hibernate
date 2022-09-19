package com.foxminded.vitaliifedan.task10.controllers;

import com.foxminded.vitaliifedan.task10.models.groups.Group;
import com.foxminded.vitaliifedan.task10.models.persons.User;
import com.foxminded.vitaliifedan.task10.models.persons.UserType;
import com.foxminded.vitaliifedan.task10.models.schedules.Audience;
import com.foxminded.vitaliifedan.task10.models.schedules.Course;
import com.foxminded.vitaliifedan.task10.models.schedules.Lecture;
import com.foxminded.vitaliifedan.task10.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/university")
public class UniversityController {

    private final AudienceService audienceService;
    private final CourseService courseService;
    private final GroupService groupService;
    private final LectureService lectureService;
    private final UserService userService;

    @Autowired
    public UniversityController(AudienceService audienceService, CourseService courseService, GroupService groupService, LectureService lectureService, UserService userService) {
        this.audienceService = audienceService;
        this.courseService = courseService;
        this.groupService = groupService;
        this.lectureService = lectureService;
        this.userService = userService;
    }

    @GetMapping()
    public String homePage() {
        return "people/index";
    }

    @GetMapping("/audiences")
    public String getAudiences(Model model) {
        List<Audience> audiences = audienceService.findAll();
        if (!audiences.isEmpty()) {
            model.addAttribute("audiences", audiences);
        } else {
            model.addAttribute("emptyAudiences", true);
        }
        return "people/index";
    }

    @GetMapping("/courses")
    public String getCourses(Model model) {
        List<Course> courses = courseService.findAll();
        if (!courses.isEmpty()) {
            model.addAttribute("courses", courses);
        } else {
            model.addAttribute("emptyCourses", true);
        }
        return "people/index";
    }

    @GetMapping("/groups")
    public String getGroups(Model model) {
        List<Group> groups = groupService.findAll();
        if (!groups.isEmpty()) {
            model.addAttribute("groups", groups);
        } else {
            model.addAttribute("emptyGroups", true);
        }
        return "people/index";
    }

    @GetMapping("/lectures")
    public String getLectures(Model model) {
        List<Lecture> lectures = lectureService.findAll();
        if (!lectures.isEmpty()) {
            List<Map<String, String>> filledLectures = new ArrayList<>();
            for (Lecture lecture : lectures) {
                filledLectures.add(lectureService.getFilledLecture(lecture.getId()));
            }
            model.addAttribute("lectures", filledLectures);
        } else {
            model.addAttribute("emptyLectures", true);
        }
        return "people/index";
    }

    @GetMapping("/users")
    public String getUsers(Model model) {
        List<User> users = userService.getUserByUserType(UserType.USER);
        if (!users.isEmpty()) {
            model.addAttribute("users", users);
        } else {
            model.addAttribute("emptyUsers", true);
        }
        return "people/index";
    }

    @GetMapping("/students")
    public String getStudents(Model model) {
        List<User> students = userService.getUserByUserType(UserType.STUDENT);
        if (!students.isEmpty()) {
            model.addAttribute("students", students);
        } else {
            model.addAttribute("emptyStudents", true);
        }
        return "people/index";
    }

    @GetMapping("/teachers")
    public String getTeachers(Model model) {
        List<User> teachers = userService.getUserByUserType(UserType.TEACHER);
        if (!teachers.isEmpty()) {
            model.addAttribute("teachers", teachers);
        } else {
            model.addAttribute("emptyTeachers", true);
        }
        return "people/index";
    }
}
