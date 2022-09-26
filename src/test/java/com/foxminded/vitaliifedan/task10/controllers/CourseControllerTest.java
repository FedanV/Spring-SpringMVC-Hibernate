package com.foxminded.vitaliifedan.task10.controllers;

import com.foxminded.vitaliifedan.task10.models.schedules.Course;
import com.foxminded.vitaliifedan.task10.services.CourseService;
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


@WebMvcTest(CourseController.class)
class CourseControllerTest {

    @MockBean
    private CourseService courseService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getCourses() throws Exception {
        Course course = new Course("Math");
        Mockito.doReturn(List.of(course)).when(courseService).findAll();
        mockMvc.perform(MockMvcRequestBuilders.get("/courses"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.view().name("university/courses/allCourses"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("courses"))
                .andExpect(MockMvcResultMatchers.model().attribute("courses", List.of(course)));
    }

    @Test
    void addCourse() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/courses/add"))
                .andExpectAll(
                        MockMvcResultMatchers.status().is2xxSuccessful(),
                        MockMvcResultMatchers.view().name("university/courses/addCourse"),
                        MockMvcResultMatchers.model().attributeExists("course"),
                        MockMvcResultMatchers.model().attribute("course", new Course())
                );
    }

    @Test
    void saveCourse() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/courses/addCourse")
                        .param("courseName", "course1"))
                .andExpectAll(
                        MockMvcResultMatchers.status().is3xxRedirection(),
                        MockMvcResultMatchers.redirectedUrl("/courses")
                );
    }

    @Test
    void showCourse() throws Exception {
        Course course = new Course();
        Mockito.doReturn(Optional.of(course)).when(courseService).findById(Mockito.anyInt());
        mockMvc.perform(MockMvcRequestBuilders.get("/courses/1/edit"))
                .andExpectAll(
                        MockMvcResultMatchers.status().is2xxSuccessful(),
                        MockMvcResultMatchers.view().name("university/courses/editCourse"),
                        MockMvcResultMatchers.model().attributeExists("course"),
                        MockMvcResultMatchers.model().attribute("course", course)
                );

    }

    @Test
    void updateCourse() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.patch("/courses/1"))
                .andExpectAll(
                        MockMvcResultMatchers.status().is3xxRedirection(),
                        MockMvcResultMatchers.redirectedUrl("/courses/1")
                );
    }

    @Test
    void deleteCourse() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/courses/1"))
                .andExpectAll(
                        MockMvcResultMatchers.status().is3xxRedirection(),
                        MockMvcResultMatchers.redirectedUrl("/courses")
                );
    }


}