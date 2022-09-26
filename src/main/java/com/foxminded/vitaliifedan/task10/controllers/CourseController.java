package com.foxminded.vitaliifedan.task10.controllers;

import com.foxminded.vitaliifedan.task10.models.schedules.Course;
import com.foxminded.vitaliifedan.task10.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public String getCourses(Model model) {
        List<Course> courses = courseService.findAll();
        model.addAttribute("courses", courses);
        return "university/courses/allCourses";
    }

    @GetMapping("/add")
    public String addCourse(@ModelAttribute Course course) {
        return "university/courses/addCourse";
    }

    @PostMapping("/addCourse")
    public String saveCourse(@ModelAttribute Course course) {
        courseService.create(course);
        return "redirect:/courses";
    }

    @GetMapping("/{id}")
    public String showCourse(@PathVariable("id") Integer id, Model model) {
        courseService.findById(id).ifPresent(entity -> model.addAttribute("course", entity));
        return "university/courses/showCourse";
    }

    @GetMapping("/{id}/edit")
    public String editCourse(@PathVariable("id") Integer id, Model model) {
        courseService.findById(id).ifPresent(entity -> model.addAttribute("course", entity));
        return "university/courses/editCourse";
    }

    @PatchMapping("/{id}")
    public String updateCourse(@PathVariable("id") Integer id, @ModelAttribute("course") Course course) {
        courseService.update(course);
        return "redirect:/courses/" + id;
    }

    @DeleteMapping("/{id}")
    public String deleteCourse(@PathVariable("id") Integer id) {
        courseService.deleteById(id);
        return "redirect:/courses";
    }

}
