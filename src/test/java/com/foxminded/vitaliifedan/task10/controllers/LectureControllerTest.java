package com.foxminded.vitaliifedan.task10.controllers;


import com.foxminded.vitaliifedan.task10.models.groups.Group;
import com.foxminded.vitaliifedan.task10.models.persons.Teacher;
import com.foxminded.vitaliifedan.task10.models.schedules.Audience;
import com.foxminded.vitaliifedan.task10.models.schedules.Course;
import com.foxminded.vitaliifedan.task10.models.schedules.Lecture;
import com.foxminded.vitaliifedan.task10.services.LectureService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@WebMvcTest(LectureController.class)
class LectureControllerTest {

    @MockBean
    private LectureService lectureService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void getLectures() throws Exception {
        Lecture lecture = new Lecture(1, new Course(), new Teacher(), LocalDate.now(), new Group(), 4, new Audience());
        Mockito.doReturn(List.of(lecture)).when(lectureService).findAll();
        Mockito.doReturn(Map.of("course", "Course1")).when(lectureService).getFilledLecture(Mockito.anyInt());
        mockMvc.perform(MockMvcRequestBuilders.get("/lectures"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.view().name("university/lecture"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("lectures"))
                .andExpect(MockMvcResultMatchers.model().attribute("lectures", List.of(Map.of("course", "Course1"))));
    }
}