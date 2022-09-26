package com.foxminded.vitaliifedan.task10.controllers;

import com.foxminded.vitaliifedan.task10.models.persons.Role;
import com.foxminded.vitaliifedan.task10.models.persons.Teacher;
import com.foxminded.vitaliifedan.task10.models.persons.User;
import com.foxminded.vitaliifedan.task10.models.persons.UserType;
import com.foxminded.vitaliifedan.task10.services.UserService;
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
    @Autowired
    private MockMvc mockMvc;

    @Test
    void getTeachers() throws Exception {
        User teacher = new User("login", "pass", Role.ROLE_TEACHER, UserType.TEACHER);
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
                        MockMvcResultMatchers.model().attributeExists("roles", "teacher"),
                        MockMvcResultMatchers.model().attribute("roles", Role.values()),
                        MockMvcResultMatchers.model().attribute("teacher", new User())
                );
    }


    @Test
    void saveTeacher() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/teachers/add")
                .param("name", "Ivan")
                .param("surname", "Ivanov")
                .param("login", "test")
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
                        MockMvcResultMatchers.model().attributeExists("teacher", "roles")
                );
    }

    @Test
    void updateTeacher() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.patch("/teachers/1"))
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
}