package com.foxminded.vitaliifedan.task10.controllers;

import com.foxminded.vitaliifedan.task10.exceptions.UserException;
import com.foxminded.vitaliifedan.task10.models.persons.Role;
import com.foxminded.vitaliifedan.task10.models.persons.User;
import com.foxminded.vitaliifedan.task10.models.persons.UserType;
import com.foxminded.vitaliifedan.task10.services.UserService;
import com.foxminded.vitaliifedan.task10.services.validators.UserValidationService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doReturn;

@WebMvcTest(UserController.class)
@WithMockUser(username = "test", password = "test", authorities = {"ADMIN"})
class UserControllerTest {

    @MockBean
    private UserService userService;
    @MockBean
    private UserValidationService userValidationService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void findAll() throws Exception {
        User user = User.builder()
                .name("name")
                .surname("surname")
                .phone("phone")
                .login("login")
                .password("pass")
                .role(Role.NONE)
                .userType(UserType.USER)
                .build();
        doReturn(List.of(user)).when(userService).getUserByUserType(UserType.USER);
        mockMvc.perform(MockMvcRequestBuilders.get("/users"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.view().name("university/users/allUsers"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("users"))
                .andExpect(MockMvcResultMatchers.model().attribute("users", List.of(user)));
    }

    @Test
    void addUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/add"))
                .andExpectAll(
                        MockMvcResultMatchers.status().is2xxSuccessful(),
                        MockMvcResultMatchers.view().name("university/users/addUser"),
                        MockMvcResultMatchers.model().attributeExists("roles", "user"),
                        MockMvcResultMatchers.model().attribute("roles", Role.values())
                );
    }


    @Test
    void saveUser() throws Exception {
        Mockito.doReturn("").when(userValidationService).validatePhoneNumber(Mockito.anyString());
        mockMvc.perform(MockMvcRequestBuilders.post("/users/add")
                .param("name", "Ivan")
                .param("surname", "Ivanov")
                .param("phone", "123456")
                .param("login", "test")
                .param("password", "1234")
                .param("role", Role.NONE.toString())
                .param("userType", UserType.USER.toString())
        ).andExpectAll(
                MockMvcResultMatchers.status().is3xxRedirection(),
                MockMvcResultMatchers.redirectedUrl("/users")
        );
    }

    @Test
    void editUser() throws Exception {
        Mockito.doReturn(Optional.of(new User())).when(userService).findById(Mockito.anyInt());
        mockMvc.perform(MockMvcRequestBuilders.get("/users/1/edit"))
                .andExpectAll(
                        MockMvcResultMatchers.status().is2xxSuccessful(),
                        MockMvcResultMatchers.view().name("university/users/editUser"),
                        MockMvcResultMatchers.model().attributeExists("user", "roles")
                );
    }

    @Test
    void updateUser() throws Exception {
        Mockito.doReturn("").when(userValidationService).validatePhoneNumber(Mockito.anyString());
        mockMvc.perform(MockMvcRequestBuilders.post("/users/1")
                        .param("name", "name")
                        .param("surname", "surname")
                        .param("login", "login")
                        .param("password", "pass")
                        .param("phone", "123456"))
                .andExpectAll(
                        MockMvcResultMatchers.status().is3xxRedirection(),
                        MockMvcResultMatchers.redirectedUrl("/users/1")
                );
    }

    @Test
    void deleteUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/users/1"))
                .andExpectAll(
                        MockMvcResultMatchers.status().is3xxRedirection(),
                        MockMvcResultMatchers.redirectedUrl("/users")
                );
    }


    @Test
    void checkUserExceptionWhenCreateUser() throws Exception {
        User user = User.builder()
                .name("name")
                .surname("surname")
                .phone("phone1")
                .login("login")
                .password("password")
                .role(Role.NONE)
                .build();
        Mockito.doThrow(UserException.class).when(userService).create(Mockito.any(User.class));
        Mockito.doReturn("").when(userValidationService).validatePhoneNumber(Mockito.anyString());
        mockMvc.perform(MockMvcRequestBuilders.post("/users/add")
                        .param("name", "name")
                        .param("surname", "surname")
                        .param("phone", "phone1")
                        .param("login", "login")
                        .param("password", "password")
                        .param("role", Role.NONE.toString()))
                .andExpectAll(
                        MockMvcResultMatchers.status().is2xxSuccessful(),
                        MockMvcResultMatchers.view().name("university/error")
                );
    }

    @Test
    void checkUserExceptionWhenUpdateUser() throws Exception {
        User user = User.builder()
                .id(1)
                .name("name")
                .surname("surname")
                .phone("phone")
                .login("login")
                .password("pass")
                .role(Role.NONE)
                .userType(UserType.USER)
                .build();
        Mockito.doThrow(UserException.class).when(userService).update(Mockito.any(User.class));
        Mockito.doReturn("").when(userValidationService).validatePhoneNumber(Mockito.anyString());
        Mockito.doReturn(Optional.of(user)).when(userService).findById(Mockito.anyInt());
        mockMvc.perform(MockMvcRequestBuilders.post("/users/1")
                        .param("id", "1")
                        .param("name", "name")
                        .param("surname", "surname")
                        .param("phone", "phone1")
                        .param("login", "login")
                        .param("password", "password")
                        .param("role", Role.NONE.toString())
                        .param("userType", UserType.USER.toString()))
                .andExpectAll(
                        MockMvcResultMatchers.status().is2xxSuccessful(),
                        MockMvcResultMatchers.view().name("university/error")
                );
    }

    @Test
    void checkUserExceptionWhenDeleteUser() throws Exception {
        Mockito.doThrow(UserException.class).when(userService).deletedById(1);
        mockMvc.perform(MockMvcRequestBuilders.delete("/users/1"))
                .andExpectAll(
                        MockMvcResultMatchers.status().is2xxSuccessful(),
                        MockMvcResultMatchers.view().name("university/error")
                );
    }

    @Test
    void checkUserPhoneWhenCreateUser() throws Exception {
        Mockito.doReturn("error").when(userValidationService).validatePhoneNumber(Mockito.anyString());
        mockMvc.perform(MockMvcRequestBuilders.post("/users/add")
                        .param("id", "1")
                        .param("name", "name")
                        .param("surname", "surname")
                        .param("phone", "phone1")
                        .param("login", "login")
                        .param("password", "password")
                        .param("role", Role.NONE.toString())
                        .param("userType", UserType.USER.toString()))
                .andExpectAll(
                        MockMvcResultMatchers.status().is2xxSuccessful(),
                        MockMvcResultMatchers.view().name("university/users/addUser"),
                        MockMvcResultMatchers.model().attributeExists("user")
                );
    }
}