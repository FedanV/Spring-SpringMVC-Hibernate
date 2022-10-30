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

@WebMvcTest(StudentController.class)
@WithMockUser(username = "test", password = "test", authorities = {"ADMIN"})
class StudentControllerTest {

    @MockBean
    private UserService userService;
    @MockBean
    private UserValidationService userValidationService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void getStudents() throws Exception {
        User student = User.builder()
                .name("name")
                .surname("surname")
                .phone("phone")
                .login("login")
                .password("pass")
                .role(Role.ROLE_STUDENT)
                .userType(UserType.STUDENT)
                .build();
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
                        MockMvcResultMatchers.model().attributeExists("roles", "user"),
                        MockMvcResultMatchers.model().attribute("roles", Role.values()),
                        MockMvcResultMatchers.model().attribute("user", new User())
                );
    }


    @Test
    void saveStudent() throws Exception {
        Mockito.doReturn("").when(userValidationService).validatePhoneNumber(Mockito.anyString());
        mockMvc.perform(MockMvcRequestBuilders.post("/students/add")
                .param("name", "Ivan")
                .param("surname", "Ivanov")
                .param("phone", "123455")
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
                        MockMvcResultMatchers.model().attributeExists("user", "roles")
                );
    }

    @Test
    void updateStudent() throws Exception {
        Mockito.doReturn("").when(userValidationService).validatePhoneNumber(Mockito.anyString());
        mockMvc.perform(MockMvcRequestBuilders.post("/students/1")
                        .param("phone", "123467")
                        .param("name", "name")
                        .param("surname", "surname")
                        .param("login", "login")
                        .param("password", "pass"))
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


    @Test
    void checkUserExceptionWhenCreateStudent() throws Exception {
        User student = User.builder()
                .name("name")
                .surname("surname")
                .phone("phone")
                .login("login")
                .password("pass")
                .role(Role.ROLE_STUDENT)
                .userType(UserType.STUDENT)
                .build();
        Mockito.doThrow(UserException.class).when(userService).create(Mockito.any(User.class));
        Mockito.doReturn("").when(userValidationService).validatePhoneNumber(Mockito.anyString());
        mockMvc.perform(MockMvcRequestBuilders.post("/students/add")
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
    void checkUserExceptionWhenUpdateStudent() throws Exception {
        User student = User.builder()
                .id(1)
                .name("name")
                .surname("surname")
                .phone("phone")
                .login("login")
                .password("pass")
                .role(Role.ROLE_STUDENT)
                .userType(UserType.STUDENT)
                .build();
        Mockito.doThrow(UserException.class).when(userService).update(student);
        Mockito.doReturn("").when(userValidationService).validatePhoneNumber(Mockito.anyString());
        Mockito.doReturn(Optional.of(student)).when(userService).findById(Mockito.anyInt());
        mockMvc.perform(MockMvcRequestBuilders.post("/students/1")
                        .param("id", "1")
                        .param("name", "name")
                        .param("surname", "surname")
                        .param("phone", "phone1")
                        .param("login", "login")
                        .param("password", "password")
                        .param("role", Role.NONE.toString())
                        .param("userType", UserType.STUDENT.toString()))
                .andExpectAll(
                        MockMvcResultMatchers.status().is2xxSuccessful(),
                        MockMvcResultMatchers.view().name("university/error")
                );
    }

    @Test
    void checkUserExceptionWhenDeleteStudent() throws Exception {
        Mockito.doThrow(UserException.class).when(userService).deletedById(1);
        mockMvc.perform(MockMvcRequestBuilders.delete("/students/1"))
                .andExpectAll(
                        MockMvcResultMatchers.status().is2xxSuccessful(),
                        MockMvcResultMatchers.view().name("university/error")
                );
    }

    @Test
    void checkUserPhoneWhenCreateStudent() throws Exception {
        Mockito.doReturn("error").when(userValidationService).validatePhoneNumber(Mockito.anyString());
        mockMvc.perform(MockMvcRequestBuilders.post("/students/add")
                        .param("id", "1")
                        .param("name", "name")
                        .param("surname", "surname")
                        .param("phone", "123456")
                        .param("login", "login")
                        .param("password", "password")
                        .param("role", Role.NONE.toString())
                        .param("userType", UserType.STUDENT.toString()))
                .andExpectAll(
                        MockMvcResultMatchers.status().is2xxSuccessful(),
                        MockMvcResultMatchers.view().name("university/students/addStudent"),
                        MockMvcResultMatchers.model().attributeExists("user")
                );
    }

}