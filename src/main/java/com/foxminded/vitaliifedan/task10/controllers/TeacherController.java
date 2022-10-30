package com.foxminded.vitaliifedan.task10.controllers;

import com.foxminded.vitaliifedan.task10.exceptions.UserException;
import com.foxminded.vitaliifedan.task10.models.persons.Role;
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
@RequestMapping("/teachers")
public class TeacherController {

    private final UserService userService;
    private final UserValidationService userValidationService;

    @Autowired
    public TeacherController(UserService userService, UserValidationService userValidationService) {
        this.userService = userService;
        this.userValidationService = userValidationService;
    }

    @GetMapping()
    public String getTeachers(Model model) {
        List<User> teachers = userService.getUserByUserType(UserType.TEACHER);
        model.addAttribute("teachers", teachers);
        return "university/teachers/allTeachers";
    }

    @GetMapping("/add")
    public String addTeacher(@ModelAttribute("user") User teacher, Model model) {
        model.addAttribute("roles", Role.values());
        return "university/teachers/addTeacher";
    }

    @PostMapping("/add")
    public String saveTeacher(@ModelAttribute("user") @Valid User teacher, BindingResult result, Model model) {
        String err = userValidationService.validatePhoneNumber(teacher.getPhone());
        if (!err.isEmpty()) {
            result.rejectValue("phone", "", err);
        }
        if (result.hasErrors()) {
            model.addAttribute("roles", Role.values());
            return "university/teachers/addTeacher";
        }
        teacher.setUserType(UserType.TEACHER);
        try {
            userService.create(teacher);
        } catch (UserException e) {
            return "university/error";
        }
        return "redirect:/teachers";
    }

    @GetMapping("/{id}")
    public String showTeacher(@PathVariable("id") Integer id, Model model) {
        userService.findById(id).ifPresent(entity -> model.addAttribute("user", entity));
        return "university/teachers/showTeacher";
    }

    @GetMapping("/{id}/edit")
    public String editTeacher(@PathVariable("id") Integer id, Model model) {
        userService.findById(id).ifPresent(entity -> model.addAttribute("user", entity));
        model.addAttribute("roles", Role.values());
        return "university/teachers/editTeacher";
    }

    @PostMapping("/{id}")
    public String updateTeacher(@PathVariable("id") Integer id, @ModelAttribute("user") @Valid User teacher, BindingResult result, Model model) {
        if (userService.findUserByPhone(teacher.getPhone()).isPresent() &&
                !userService.findUserByPhone(teacher.getPhone()).get().getId().equals(id)) {
            result.rejectValue("phone", "", userValidationService.validatePhoneNumber(teacher.getPhone()));
        }
        if (result.hasErrors()) {
            model.addAttribute("roles", Role.values());
            return "university/teachers/editTeacher";
        }
        Optional<User> updateUser = userService.findById(id);
        if (updateUser.isPresent()) {
            updateUser.get().setName(teacher.getName());
            updateUser.get().setSurname(teacher.getSurname());
            updateUser.get().setPhone(teacher.getPhone());
            if (teacher.getPassword() != null) {
                updateUser.get().setPassword(teacher.getPassword());
            }
            if (teacher.getLogin() != null) {
                updateUser.get().setLogin(teacher.getLogin());
            }
            if (teacher.getRole() != null) {
                updateUser.get().setRole(teacher.getRole());
            }
            try {
                userService.update(updateUser.get());
            } catch (UserException e) {
                return "university/error";
            }
        }
        return "redirect:/teachers/" + id;
    }

    @DeleteMapping("/{id}")
    public String deleteTeacher(@PathVariable("id") Integer id) {
        try {
            userService.deletedById(id);
        } catch (UserException e) {
            return "university/error";
        }
        return "redirect:/teachers";
    }
}
