package com.foxminded.vitaliifedan.task10.controllers;

import com.foxminded.vitaliifedan.task10.models.groups.Group;
import com.foxminded.vitaliifedan.task10.models.persons.Teacher;
import com.foxminded.vitaliifedan.task10.models.schedules.Audience;
import com.foxminded.vitaliifedan.task10.models.schedules.Course;
import com.foxminded.vitaliifedan.task10.models.schedules.Lecture;
import com.foxminded.vitaliifedan.task10.services.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@WebMvcTest(LectureController.class)
@WithMockUser(username = "test", password = "test", authorities = {"ADMIN"})
class LectureControllerTest {

    @MockBean
    private LectureService lectureService;
    @MockBean
    private TeacherService teacherService;
    @MockBean
    private GroupService groupService;
    @MockBean
    private UserService userService;
    @MockBean
    private AudienceService audienceService;
    @MockBean
    private CourseService courseService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void getLectures() throws Exception {
        Lecture lecture = Lecture.builder()
                .id(1)
                .course(new Course())
                .teacher(new Teacher())
                .lectureDate(LocalDate.now())
                .group(new Group())
                .pairNumber(4)
                .audience(new Audience())
                .build();
        Mockito.doReturn(List.of(lecture)).when(lectureService).findAll();
        Mockito.doReturn(Map.of("course", "Course1")).when(lectureService).getFilledLecture(Mockito.anyInt());
        mockMvc.perform(MockMvcRequestBuilders.get("/lectures"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.view().name("university/lectures/allLectures"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("lectures"))
                .andExpect(MockMvcResultMatchers.model().attribute("lectures", List.of(Map.of("course", "Course1"))));
    }

    @Test
    void addLecture() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/lectures/add"))
                .andExpectAll(
                        MockMvcResultMatchers.status().is2xxSuccessful(),
                        MockMvcResultMatchers.view().name("university/lectures/addLecture"),
                        MockMvcResultMatchers.model().attributeExists("courses", "teachers", "groups", "audiences", "lecture")
                );
    }

    @Test
    void saveLecture() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/lectures/add")
                .param("courseId", "1")
                .param("teacherId", "2")
                .param("groupId", "3")
                .param("audienceId", "4")
                .param("date", "29-09-2029")
                .param("pairNumber", "5")
        ).andExpectAll(
                MockMvcResultMatchers.status().is3xxRedirection(),
                MockMvcResultMatchers.redirectedUrl("/lectures")
        );
    }

    @Test
    void editLecture() throws Exception {
        Lecture lecture = Lecture.builder()
                .id(1)
                .course(Course.builder().id(1).build())
                .teacher(Teacher.builder().id(2).build())
                .lectureDate(LocalDate.now())
                .group(Group.builder().id(1).build())
                .pairNumber(4)
                .audience(Audience.builder().id(4).build())
                .build();
        Mockito.doReturn(Optional.of(lecture)).when(lectureService).findById(Mockito.anyInt());
        mockMvc.perform(MockMvcRequestBuilders.get("/lectures/1/edit"))
                .andExpectAll(
                        MockMvcResultMatchers.status().is2xxSuccessful(),
                        MockMvcResultMatchers.view().name("university/lectures/editLecture"),
                        MockMvcResultMatchers.model().attributeExists("lecture", "courses", "teachers", "groups", "audiences")
                );
    }

    @Test
    void updateLecture() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/lectures/1")
                        .param("id", "1")
                        .param("courseId", "2")
                        .param("teacherId", "3")
                        .param("groupId", "4")
                        .param("audienceId", "5")
                        .param("date", "29-09-2022")
                        .param("pairNumber", "6")
                )
                .andExpectAll(
                        MockMvcResultMatchers.status().is3xxRedirection(),
                        MockMvcResultMatchers.redirectedUrl("/lectures/1")
                );
    }

    @Test
    void deleteLecture() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/lectures/1"))
                .andExpectAll(
                        MockMvcResultMatchers.status().is3xxRedirection(),
                        MockMvcResultMatchers.redirectedUrl("/lectures")
                );
    }
}