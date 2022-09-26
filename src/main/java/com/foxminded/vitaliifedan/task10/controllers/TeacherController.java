package com.foxminded.vitaliifedan.task10.controllers;

import com.foxminded.vitaliifedan.task10.models.persons.Role;
import com.foxminded.vitaliifedan.task10.models.persons.User;
import com.foxminded.vitaliifedan.task10.models.persons.UserType;
import com.foxminded.vitaliifedan.task10.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/teachers")
public class TeacherController {

    private final UserService userService;

    @Autowired
    public TeacherController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String getTeachers(Model model) {
        List<User> teachers = userService.getUserByUserType(UserType.TEACHER);
        model.addAttribute("teachers", teachers);
        return "university/teachers/allTeachers";
    }

    @GetMapping("/add")
    public String addTeacher(@ModelAttribute("teacher") User teacher, Model model) {
        model.addAttribute("roles", Role.values());
        return "university/teachers/addTeacher";
    }

    @PostMapping("/add")
    public String saveTeacher(@ModelAttribute("teacher") User teacher) {
        teacher.setUserType(UserType.TEACHER);
        userService.create(teacher);
        return "redirect:/teachers";
    }

    @GetMapping("/{id}")
    public String showTeacher(@PathVariable("id") Integer id, Model model) {
        userService.findById(id).ifPresent(entity -> model.addAttribute("teacher", entity));
        return "university/teachers/showTeacher";
    }

    @GetMapping("/{id}/edit")
    public String editTeacher(@PathVariable("id") Integer id, Model model) {
        userService.findById(id).ifPresent(entity -> model.addAttribute("teacher", entity));
        model.addAttribute("roles", Role.values());
        return "university/teachers/editTeacher";
    }

    @PatchMapping("/{id}")
    public String updateTeacher(@PathVariable("id") Integer id, @ModelAttribute("teacher") User teacher) {
        teacher.setUserType(UserType.TEACHER);
        teacher.setId(id);
        userService.update(teacher);
        return "redirect:/teachers/" + id;
    }

    @DeleteMapping("/{id}")
    public String deleteTeacher(@PathVariable("id") Integer id) {
        userService.deletedById(id);
        return "redirect:/teachers";
    }
}
