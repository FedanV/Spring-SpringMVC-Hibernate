package com.foxminded.vitaliifedan.task10.controllers;

import com.foxminded.vitaliifedan.task10.exceptions.CourseException;
import com.foxminded.vitaliifedan.task10.models.schedules.Course;
import com.foxminded.vitaliifedan.task10.services.CourseService;
import com.foxminded.vitaliifedan.task10.services.validators.CourseValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;
    private final CourseValidationService courseValidationService;

    @Autowired
    public CourseController(CourseService courseService, CourseValidationService courseValidationService) {
        this.courseService = courseService;
        this.courseValidationService = courseValidationService;
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
    public String saveCourse(@ModelAttribute @Valid Course course, BindingResult result) {
        String err = courseValidationService.validateCourseName(course.getCourseName());
        if (!err.isEmpty()) {
            result.rejectValue("courseName", "", err);
        }
        if (result.hasErrors()) {
            return "university/courses/addCourse";
        }
        try {
            courseService.create(course);
        } catch (CourseException e) {
            return "university/error";
        }
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

    @PostMapping("/{id}")
    public String updateCourse(@PathVariable("id") Integer id, @ModelAttribute("course") @Valid Course course, BindingResult result) {
        if (courseService.findCourseByName(course.getCourseName()).isPresent() &&
                !courseService.findCourseByName(course.getCourseName()).get().getId().equals(id)) {
            result.rejectValue("courseName", "", courseValidationService.validateCourseName(course.getCourseName()));
        }
        if (result.hasErrors()) {
            return "university/courses/editCourse";
        }
        try {
            courseService.update(course);
        } catch (CourseException e) {
            return "university/error";
        }
        return "redirect:/courses/" + id;
    }

    @DeleteMapping("/{id}")
    public String deleteCourse(@PathVariable("id") Integer id) {
        try {
            courseService.deleteById(id);
        } catch (CourseException e) {
            return "university/error";
        }
        return "redirect:/courses";
    }

}
