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
@RequestMapping("/students")
public class StudentController {

    private final UserService userService;

    @Autowired
    public StudentController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String getStudents(Model model) {
        List<User> students = userService.getUserByUserType(UserType.STUDENT);
        model.addAttribute("students", students);
        return "university/students/allStudents";
    }


    @GetMapping("/add")
    public String addStudent(@ModelAttribute("student") User student, Model model) {
        model.addAttribute("roles", Role.values());
        return "university/students/addStudent";
    }

    @PostMapping("/add")
    public String saveStudent(@ModelAttribute("student") User student) {
        student.setUserType(UserType.STUDENT);
        userService.create(student);
        return "redirect:/students";
    }

    @GetMapping("/{id}")
    public String showStudent(@PathVariable("id") Integer id, Model model) {
        userService.findById(id).ifPresent(entity -> model.addAttribute("student", entity));
        return "university/students/showStudent";
    }

    @GetMapping("/{id}/edit")
    public String editStudent(@PathVariable("id") Integer id, Model model) {
        userService.findById(id).ifPresent(entity -> model.addAttribute("student", entity));
        model.addAttribute("roles", Role.values());
        return "university/students/editStudent";
    }

    @PatchMapping("/{id}")
    public String updateStudent(@PathVariable("id") Integer id, @ModelAttribute("student") User student) {
        student.setUserType(UserType.STUDENT);
        student.setId(id);
        userService.update(student);
        return "redirect:/students/" + id;
    }

    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable("id") Integer id) {
        userService.deletedById(id);
        return "redirect:/students";
    }
}
