package com.foxminded.vitaliifedan.task10.controllers;

import com.foxminded.vitaliifedan.task10.dto.UserRegistrationDTO;
import com.foxminded.vitaliifedan.task10.exceptions.UserException;
import com.foxminded.vitaliifedan.task10.models.persons.Role;
import com.foxminded.vitaliifedan.task10.models.persons.User;
import com.foxminded.vitaliifedan.task10.models.persons.UserType;
import com.foxminded.vitaliifedan.task10.services.UserService;
import com.foxminded.vitaliifedan.task10.services.validators.UserValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final UserValidationService userValidationService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserService userService, UserValidationService userValidationService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.userValidationService = userValidationService;
        this.passwordEncoder = passwordEncoder;
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
    public String saveUser(@ModelAttribute("user") @Valid User user, BindingResult result, Model model) {
        String err = userValidationService.validatePhoneNumber(user.getPhone());
        if (!err.isEmpty()) {
            result.rejectValue("phone", "", err);
        }
        if (result.hasErrors()) {
            model.addAttribute("roles", Role.values());
            return "university/users/addUser";
        }
        user.setUserType(UserType.USER);
        try {
            userService.create(user);
        } catch (UserException e) {
            return "university/error";
        }
        return "redirect:/users";
    }

    @GetMapping("/{id}")
    public String showUser(@PathVariable("id") Integer id, Model model) {
        String username = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        userService.findById(id).ifPresent(user -> {
            model.addAttribute("user", user);
            if (user.getLogin().equals(username)) {
                model.addAttribute("owner", true);
            }
        });
        return "university/users/showUser";
    }

    @GetMapping("/{id}/edit")
    public String editUser(@PathVariable("id") Integer id, Model model) {
        userService.findById(id).ifPresent(entity -> model.addAttribute("user", entity));
        model.addAttribute("roles", Role.values());
        return "university/users/editUser";
    }

    @PostMapping("/{id}")
    public String updateUser(@PathVariable("id") Integer id, @ModelAttribute("user") @Valid User user, BindingResult result, Model model) {
        if (userService.findUserByPhone(user.getPhone()).isPresent() &&
                !userService.findUserByPhone(user.getPhone()).get().getId().equals(id)) {
            result.rejectValue("phone", "", userValidationService.validatePhoneNumber(user.getPhone()));
        }
        if (result.hasErrors()) {
            model.addAttribute("roles", Role.values());
            return "university/users/editUser";
        }
        Optional<User> updateUser = userService.findById(id);
        if (updateUser.isPresent()) {
            updateUser.get().setName(user.getName());
            updateUser.get().setSurname(user.getSurname());
            updateUser.get().setPhone(user.getPhone());
            if (user.getPassword() != null) {
                updateUser.get().setPassword(user.getPassword());
            }
            if (user.getLogin() != null) {
                updateUser.get().setLogin(user.getLogin());
            }
            if (user.getRole() != null) {
                updateUser.get().setRole(user.getRole());
            }
            try {
                userService.update(updateUser.get());
            } catch (UserException e) {
                return "university/error";
            }
        }
        return "redirect:/users/" + id;
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") Integer id) {
        try {
            userService.deletedById(id);
        } catch (UserException e) {
            return "university/error";
        }
        return "redirect:/users";
    }

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("user") UserRegistrationDTO user) {
        return "university/registration";
    }

    @PostMapping("/registration")
    public String userRegistration(@ModelAttribute("user") @Valid UserRegistrationDTO user, BindingResult result) {
        String loginError = userValidationService.validateLogin(user.getLogin());
        if (!loginError.isEmpty()) {
            result.rejectValue("login", "", loginError);
        }
        if (result.hasErrors()) {
            return "university/registration";
        }
        User registeredUser = User.builder()
                .login(user.getLogin())
                .password(passwordEncoder.encode(user.getPassword()))
                .role(Role.NONE)
                .userType(UserType.USER)
                .build();
        try {
            userService.create(registeredUser);
        } catch (UserException e) {
            return "university/error";
        }
        return "redirect:/login";
    }

}
