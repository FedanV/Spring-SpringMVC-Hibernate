package com.foxminded.vitaliifedan.task10.controllers;

import com.foxminded.vitaliifedan.task10.models.persons.Role;
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

@WebMvcTest(StudentController.class)
class StudentControllerTest {

    @MockBean
    private UserService userService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void getStudents() throws Exception {
        User student = new User("login", "pass", Role.ROLE_STUDENT, UserType.STUDENT);
        doReturn(List.of(student)).when(userService).getUserByUserType(UserType.STUDENT);
        mockMvc.perform(MockMvcRequestBuilders.get("/students"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.view().name("university/students/allStudents"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("students"))
                .andExpect(MockMvcResultMatchers.model().attribute("students", List.of(student)));
    }


    @Test
    void addStudent() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/students/add"))
                .andExpectAll(
                        MockMvcResultMatchers.status().is2xxSuccessful(),
                        MockMvcResultMatchers.view().name("university/students/addStudent"),
                        MockMvcResultMatchers.model().attributeExists("roles", "student"),
                        MockMvcResultMatchers.model().attribute("roles", Role.values()),
                        MockMvcResultMatchers.model().attribute("student", new User())
                );
    }


    @Test
    void saveStudent() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/students/add")
                .param("name", "Ivan")
                .param("surname", "Ivanov")
                .param("login", "test")
                .param("password", "1234")
                .param("role", Role.NONE.toString())
                .param("userType", UserType.STUDENT.toString())
        ).andExpectAll(
                MockMvcResultMatchers.status().is3xxRedirection(),
                MockMvcResultMatchers.redirectedUrl("/students")
        );
    }

    @Test
    void editStudent() throws Exception {
        Mockito.doReturn(Optional.of(new User())).when(userService).findById(Mockito.anyInt());
        mockMvc.perform(MockMvcRequestBuilders.get("/students/1/edit"))
                .andExpectAll(
                        MockMvcResultMatchers.status().is2xxSuccessful(),
                        MockMvcResultMatchers.view().name("university/students/editStudent"),
                        MockMvcResultMatchers.model().attributeExists("student", "roles")
                );
    }

    @Test
    void updateStudent() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.patch("/students/1"))
                .andExpectAll(
                        MockMvcResultMatchers.status().is3xxRedirection(),
                        MockMvcResultMatchers.redirectedUrl("/students/1")
                );
    }

    @Test
    void deleteStudent() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/students/1"))
                .andExpectAll(
                        MockMvcResultMatchers.status().is3xxRedirection(),
                        MockMvcResultMatchers.redirectedUrl("/students")
                );
    }

}