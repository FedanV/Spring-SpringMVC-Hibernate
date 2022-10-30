package com.foxminded.vitaliifedan.task10.controllers;

import com.foxminded.vitaliifedan.task10.dto.LectureDTO;
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
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/lectures")
public class LectureController {

    private final LectureService lectureService;
    private final CourseService courseService;
    private final UserService userService;
    private final GroupService groupService;
    private final AudienceService audienceService;

    @Autowired
    public LectureController(LectureService lectureService, CourseService courseService, UserService userService, GroupService groupService, AudienceService audienceService) {
        this.lectureService = lectureService;
        this.courseService = courseService;
        this.userService = userService;
        this.groupService = groupService;
        this.audienceService = audienceService;
    }


    @GetMapping()
    public String getLectures(Model model) {
        List<Lecture> lectures = lectureService.findAll();
        if (!lectures.isEmpty()) {
            List<Map<String, String>> filledLectures = new ArrayList<>();
            for (Lecture lecture : lectures) {
                filledLectures.add(lectureService.getFilledLecture(lecture.getId()));
            }
            model.addAttribute("lectures", filledLectures);
        }
        return "university/lectures/allLectures";
    }

    @GetMapping("/add")
    public String addLecture(@ModelAttribute("lecture") LectureDTO lecture, Model model) {
        model.addAttribute("courses", courseService.findAll());
        model.addAttribute("teachers", userService.getUserByUserType(UserType.TEACHER));
        model.addAttribute("groups", groupService.findAll());
        model.addAttribute("audiences", audienceService.findAll());
        return "university/lectures/addLecture";
    }

    @PostMapping("/add")
    public String saveLecture(@ModelAttribute LectureDTO lectureDTO) {
        Lecture lecture = Lecture.builder()
                .course(Course.builder().id(lectureDTO.getCourseId()).build())
                .teacher(User.builder().id(lectureDTO.getTeacherId()).build())
                .group(Group.builder().id(lectureDTO.getGroupId()).build())
                .audience(Audience.builder().id(lectureDTO.getAudienceId()).build())
                .lectureDate(LocalDate.parse(lectureDTO.getDate(), DateTimeFormatter.ofPattern("dd-MM-yyyy")))
                .pairNumber(lectureDTO.getPairNumber())
                .build();
        lectureService.create(lecture);
        return "redirect:/lectures";
    }

    @GetMapping("/{id}")
    public String showLecture(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("lecture", lectureService.getFilledLecture(id));
        return "university/lectures/showLecture";
    }

    @GetMapping("/{id}/edit")
    public String editLecture(@PathVariable("id") Integer id, Model model) {
        Optional<Lecture> lecture = lectureService.findById(id);
        if (lecture.isPresent()) {
            LectureDTO lectureDTO = new LectureDTO(
                    lecture.get().getId(),
                    lecture.get().getCourse().getId(),
                    lecture.get().getTeacher().getId(),
                    lecture.get().getGroup().getId(),
                    lecture.get().getAudience().getId(),
                    lecture.get().getLectureDate().toString(),
                    lecture.get().getAudience().getId()
            );
            model.addAttribute("lecture", lectureDTO);
        } else {
            model.addAttribute("lecture", new LectureDTO());
        }
        model.addAttribute("courses", courseService.findAll());
        model.addAttribute("teachers", userService.getUserByUserType(UserType.TEACHER));
        model.addAttribute("groups", groupService.findAll());
        model.addAttribute("audiences", audienceService.findAll());
        return "university/lectures/editLecture";
    }

    @PostMapping("/{id}")
    public String updateLecture(@PathVariable("id") Integer id, @ModelAttribute LectureDTO lectureDTO) {
        Lecture lecture = Lecture.builder()
                .id(id)
                .course(Course.builder().id(lectureDTO.getCourseId()).build())
                .teacher(User.builder().id(lectureDTO.getTeacherId()).build())
                .group(Group.builder().id(lectureDTO.getGroupId()).build())
                .audience(Audience.builder().id(lectureDTO.getAudienceId()).build())
                .lectureDate(LocalDate.parse(lectureDTO.getDate(), DateTimeFormatter.ofPattern("dd-MM-yyyy")))
                .pairNumber(lectureDTO.getPairNumber())
                .build();
        lectureService.update(lecture);
        return "redirect:/lectures/" + id;
    }

    @DeleteMapping("/{id}")
    public String deleteLecture(@PathVariable("id") Integer id) {
        lectureService.deletedById(id);
        return "redirect:/lectures";
    }
}
