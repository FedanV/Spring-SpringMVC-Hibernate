package com.foxminded.vitaliifedan.task10.controllers;

import com.foxminded.vitaliifedan.task10.models.persons.User;
import com.foxminded.vitaliifedan.task10.models.persons.UserType;
import com.foxminded.vitaliifedan.task10.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
        return "university/student";
    }
}
