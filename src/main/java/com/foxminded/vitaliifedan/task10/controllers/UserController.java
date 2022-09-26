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
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String getUsers(Model model) {
        List<User> users = userService.getUserByUserType(UserType.USER);
        model.addAttribute("users", users);
        return "university/users/allUsers";
    }

    @GetMapping("/add")
    public String addUser(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("roles", Role.values());
        return "university/users/addUser";
    }

    @PostMapping("/add")
    public String saveUser(@ModelAttribute("user") User user) {
        user.setUserType(UserType.USER);
        userService.create(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}")
    public String showUser(@PathVariable("id") Integer id, Model model) {
        userService.findById(id).ifPresent(entity -> model.addAttribute("user", entity));
        return "university/users/showUser";
    }

    @GetMapping("/{id}/edit")
    public String editUser(@PathVariable("id") Integer id, Model model) {
        userService.findById(id).ifPresent(entity -> model.addAttribute("user", entity));
        model.addAttribute("roles", Role.values());
        return "university/users/editUser";
    }

    @PatchMapping("/{id}")
    public String updateUser(@PathVariable("id") Integer id, @ModelAttribute("user") User user) {
        user.setUserType(UserType.USER);
        user.setId(id);
        userService.update(user);
        return "redirect:/users/" + id;
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") Integer id) {
        userService.deletedById(id);
        return "redirect:/users";
    }

}
