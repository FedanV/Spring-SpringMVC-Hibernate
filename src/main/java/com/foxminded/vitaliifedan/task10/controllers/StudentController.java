package com.foxminded.vitaliifedan.task10.controllers;

import com.foxminded.vitaliifedan.task10.exceptions.UserException;
import com.foxminded.vitaliifedan.task10.models.persons.Role;
import com.foxminded.vitaliifedan.task10.models.persons.Student;
import com.foxminded.vitaliifedan.task10.models.persons.User;
import com.foxminded.vitaliifedan.task10.models.persons.UserType;
import com.foxminded.vitaliifedan.task10.services.UserService;
import com.foxminded.vitaliifedan.task10.services.validators.UserValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/students")
public class StudentController {

    private final UserService userService;
    private final UserValidationService userValidationService;

    @Autowired
    public StudentController(UserService userService, UserValidationService userValidationService) {
        this.userService = userService;
        this.userValidationService = userValidationService;
    }

    @GetMapping()
    public String getStudents(Model model) {
        List<User> students = userService.getUserByUserType(UserType.STUDENT);
        model.addAttribute("students", students);
        return "university/students/allStudents";
    }


    @GetMapping("/add")
    public String addStudent(@ModelAttribute("user") Student student, Model model) {
        model.addAttribute("roles", Role.values());
        return "university/students/addStudent";
    }

    @PostMapping("/add")
    public String saveStudent(@ModelAttribute("user") @Valid Student student, BindingResult result, Model model) {
        String err = userValidationService.validatePhoneNumber(student.getPhone());
        if (!err.isEmpty()) {
            result.rejectValue("phone", "", err);
        }
        if (result.hasErrors()) {
            model.addAttribute("roles", Role.values());
            return "university/students/addStudent";
        }
        try {
            userService.create(student);
        } catch (UserException e) {
            return "university/error";
        }
        return "redirect:/students";
    }

    @GetMapping("/{id}")
    public String showStudent(@PathVariable("id") Integer id, Model model) {
        userService.findById(id).ifPresent(entity -> model.addAttribute("user", entity));
        return "university/students/showStudent";
    }

    @GetMapping("/{id}/edit")
    public String editStudent(@PathVariable("id") Integer id, Model model) {
        userService.findById(id).ifPresent(entity -> model.addAttribute("user", entity));
        model.addAttribute("roles", Role.values());
        return "university/students/editStudent";
    }

    @PostMapping("/{id}")
    public String updateStudent(@PathVariable("id") Integer id, @ModelAttribute("user") @Valid Student student, BindingResult result, Model model) {
        if (userService.findUserByPhone(student.getPhone()).isPresent() &&
                !userService.findUserByPhone(student.getPhone()).get().getId().equals(id)) {
            result.rejectValue("phone", "", userValidationService.validatePhoneNumber(student.getPhone()));
        }
        if (result.hasErrors()) {
            return "university/students/editStudent";
        }
        Optional<User> updateUser = userService.findById(id);
        if (updateUser.isPresent()) {
            updateUser.get().setName(student.getName());
            updateUser.get().setSurname(student.getSurname());
            updateUser.get().setPhone(student.getPhone());
            if (student.getPassword() != null) {
                updateUser.get().setPassword(student.getPassword());
            }
            if (student.getLogin() != null) {
                updateUser.get().setLogin(student.getLogin());
            }
            if (student.getRole() != null) {
                updateUser.get().setRole(student.getRole());
            }
            try {
                userService.update(updateUser.get());
            } catch (UserException e) {
                return "university/error";
            }
        }
        return "redirect:/students/" + id;
    }

    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable("id") Integer id) {
        try {
            userService.deletedById(id);
        } catch (UserException e) {
            return "university/error";
        }
        return "redirect:/students";
    }
}
