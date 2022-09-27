package com.foxminded.vitaliifedan.task10.controllers;

import com.foxminded.vitaliifedan.task10.exceptions.UserException;
import com.foxminded.vitaliifedan.task10.models.persons.Role;
import com.foxminded.vitaliifedan.task10.models.persons.Teacher;
import com.foxminded.vitaliifedan.task10.models.persons.User;
import com.foxminded.vitaliifedan.task10.models.persons.UserType;
import com.foxminded.vitaliifedan.task10.services.UserService;
import com.foxminded.vitaliifedan.task10.services.validators.UserValidationService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doReturn;

@WebMvcTest(TeacherController.class)
class TeacherControllerTest {

    @MockBean
    private UserService userService;
    @MockBean
    private UserValidationService userValidationService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void getTeachers() throws Exception {
        User teacher = new User("name", "surname", "phone", "login", "pass", Role.ROLE_TEACHER, UserType.TEACHER);
        doReturn(List.of(teacher)).when(userService).getUserByUserType(UserType.TEACHER);
        mockMvc.perform(MockMvcRequestBuilders.get("/teachers"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.view().name("university/teachers/allTeachers"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("teachers"))
                .andExpect(MockMvcResultMatchers.model().attribute("teachers", List.of(teacher)));
    }


    @Test
    void addTeacher() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/teachers/add"))
                .andExpectAll(
                        MockMvcResultMatchers.status().is2xxSuccessful(),
                        MockMvcResultMatchers.view().name("university/teachers/addTeacher"),
                        MockMvcResultMatchers.model().attributeExists("roles", "user"),
                        MockMvcResultMatchers.model().attribute("roles", Role.values()),
                        MockMvcResultMatchers.model().attribute("user", new Teacher())
                );
    }


    @Test
    void saveTeacher() throws Exception {
        Mockito.doReturn("").when(userValidationService).validatePhoneNumber(Mockito.anyString());
        mockMvc.perform(MockMvcRequestBuilders.post("/teachers/add")
                .param("name", "Ivan")
                .param("surname", "Ivanov")
                .param("login", "test")
                .param("phone", "123456")
                .param("password", "1234")
                .param("role", Role.NONE.toString())
                .param("userType", UserType.TEACHER.toString())
        ).andExpectAll(
                MockMvcResultMatchers.status().is3xxRedirection(),
                MockMvcResultMatchers.redirectedUrl("/teachers")
        );
    }

    @Test
    void editTeacher() throws Exception {
        Mockito.doReturn(Optional.of(new User())).when(userService).findById(Mockito.anyInt());
        mockMvc.perform(MockMvcRequestBuilders.get("/teachers/1/edit"))
                .andExpectAll(
                        MockMvcResultMatchers.status().is2xxSuccessful(),
                        MockMvcResultMatchers.view().name("university/teachers/editTeacher"),
                        MockMvcResultMatchers.model().attributeExists("user", "roles")
                );
    }

    @Test
    void updateTeacher() throws Exception {
        Mockito.doReturn("").when(userValidationService).validatePhoneNumber(Mockito.anyString());
        mockMvc.perform(MockMvcRequestBuilders.post("/teachers/1")
                        .param("name", "name")
                        .param("surname", "surname")
                        .param("login", "login")
                        .param("password", "pass")
                        .param("phone", "123456"))
                .andExpectAll(
                        MockMvcResultMatchers.status().is3xxRedirection(),
                        MockMvcResultMatchers.redirectedUrl("/teachers/1")
                );
    }

    @Test
    void deleteTeacher() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/teachers/1"))
                .andExpectAll(
                        MockMvcResultMatchers.status().is3xxRedirection(),
                        MockMvcResultMatchers.redirectedUrl("/teachers")
                );
    }


    @Test
    void checkUserExceptionWhenCreateTeacher() throws Exception {
        Mockito.doThrow(UserException.class).when(userService).create(new User("name", "surname", "phone1", "login", "password", Role.NONE, UserType.TEACHER));
        Mockito.doReturn("").when(userValidationService).validatePhoneNumber(Mockito.anyString());
        mockMvc.perform(MockMvcRequestBuilders.post("/teachers/add")
                        .param("name", "name")
                        .param("surname", "surname")
                        .param("phone", "phone1")
                        .param("login", "login")
                        .param("password", "password")
                        .param("role", Role.NONE.toString())
                        .param("userType", UserType.TEACHER.toString()))
                .andExpectAll(
                        MockMvcResultMatchers.status().is2xxSuccessful(),
                        MockMvcResultMatchers.view().name("university/error")
                );
    }

    @Test
    void checkUserExceptionWhenUpdateTeacher() throws Exception {
        Mockito.doThrow(UserException.class).when(userService).update(new User(1, "name", "surname", "phone1", "login", "password", Role.NONE, UserType.TEACHER));
        Mockito.doReturn("").when(userValidationService).validatePhoneNumber(Mockito.anyString());
        mockMvc.perform(MockMvcRequestBuilders.post("/teachers/1")
                        .param("id", "1")
                        .param("name", "name")
                        .param("surname", "surname")
                        .param("phone", "phone1")
                        .param("login", "login")
                        .param("password", "password")
                        .param("role", Role.NONE.toString())
                        .param("userType", UserType.TEACHER.toString()))
                .andExpectAll(
                        MockMvcResultMatchers.status().is2xxSuccessful(),
                        MockMvcResultMatchers.view().name("university/error")
                );
    }

    @Test
    void checkUserExceptionWhenDeleteTeacher() throws Exception {
        Mockito.doThrow(UserException.class).when(userService).deletedById(1);
        mockMvc.perform(MockMvcRequestBuilders.delete("/teachers/1"))
                .andExpectAll(
                        MockMvcResultMatchers.status().is2xxSuccessful(),
                        MockMvcResultMatchers.view().name("university/error")
                );
    }

    @Test
    void checkUserPhoneWhenCreateTeacher() throws Exception {
        Mockito.doReturn("error").when(userValidationService).validatePhoneNumber(Mockito.anyString());
        mockMvc.perform(MockMvcRequestBuilders.post("/teachers/add")
                        .param("id", "1")
                        .param("name", "name")
                        .param("surname", "surname")
                        .param("phone", "123456")
                        .param("login", "login")
                        .param("password", "password")
                        .param("role", Role.NONE.toString())
                        .param("userType", UserType.TEACHER.toString()))
                .andExpectAll(
                        MockMvcResultMatchers.status().is2xxSuccessful(),
                        MockMvcResultMatchers.view().name("university/teachers/addTeacher"),
                        MockMvcResultMatchers.model().attributeExists("user")
                );
    }
}