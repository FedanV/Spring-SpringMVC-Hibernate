package com.foxminded.vitaliifedan.task10.controllers;

import com.foxminded.vitaliifedan.task10.models.schedules.Lecture;
import com.foxminded.vitaliifedan.task10.services.LectureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/lectures")
public class LectureController {

    private final LectureService lectureService;

    @Autowired
    public LectureController(LectureService lectureService) {
        this.lectureService = lectureService;
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
        return "university/lecture";
    }
}
