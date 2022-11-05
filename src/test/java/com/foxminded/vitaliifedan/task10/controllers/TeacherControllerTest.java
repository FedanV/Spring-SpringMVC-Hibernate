package com.foxminded.vitaliifedan.task10.controllers;

import com.foxminded.vitaliifedan.task10.models.persons.Role;
import com.foxminded.vitaliifedan.task10.models.persons.Teacher;
import com.foxminded.vitaliifedan.task10.models.persons.User;
import com.foxminded.vitaliifedan.task10.models.persons.UserType;
import com.foxminded.vitaliifedan.task10.services.TeacherService;
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

@WebMvcTest(TeacherController.class)
@WithMockUser(username = "test", password = "test", authorities = {"ADMIN"})
class TeacherControllerTest {

    @MockBean
    private UserService userService;
    @MockBean
    private TeacherService teacherService;
    @MockBean
    private UserValidationService userValidationService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void getTeachers() throws Exception {
        Teacher teacher = Teacher.builder()
                .name("name")
                .surname("surname")
                .phone("phone")
                .login("login")
                .password("pass")
                .role(Role.ROLE_TEACHER)
                .build();
        doReturn(List.of(teacher)).when(teacherService).findAll();
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
                        MockMvcResultMatchers.model().attribute("roles", Role.values())
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