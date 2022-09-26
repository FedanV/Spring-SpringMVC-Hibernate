package com.foxminded.vitaliifedan.task10.controllers;

import com.foxminded.vitaliifedan.task10.models.schedules.Audience;
import com.foxminded.vitaliifedan.task10.models.schedules.Course;
import com.foxminded.vitaliifedan.task10.services.AudienceService;
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


@WebMvcTest(AudienceController.class)
class AudienceControllerTest {

    @MockBean
    private AudienceService audienceService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAudiences() throws Exception {
        Audience audience = new Audience(10);
        Mockito.doReturn(List.of(audience)).when(audienceService).findAll();
        mockMvc.perform(MockMvcRequestBuilders.get("/audiences"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.view().name("university/audiences/allAudiences"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("audiences"))
                .andExpect(MockMvcResultMatchers.model().attribute("audiences", List.of(audience)));
    }

    @Test
    void addAudience() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/audiences/add"))
                .andExpectAll(
                        MockMvcResultMatchers.status().is2xxSuccessful(),
                        MockMvcResultMatchers.view().name("university/audiences/addAudience"),
                        MockMvcResultMatchers.model().attributeExists("audience"),
                        MockMvcResultMatchers.model().attribute("audience", new Audience())
                );
    }

    @Test
    void saveAudience() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/audiences/addAudience")
                        .param("roomNumber", "1"))
                .andExpectAll(
                        MockMvcResultMatchers.status().is3xxRedirection(),
                        MockMvcResultMatchers.redirectedUrl("/audiences")
                );
    }

    @Test
    void showAudience() throws Exception {
        Audience audience = new Audience();
        Mockito.doReturn(Optional.of(audience)).when(audienceService).findById(Mockito.anyInt());
        mockMvc.perform(MockMvcRequestBuilders.get("/audiences/1/edit"))
                .andExpectAll(
                        MockMvcResultMatchers.status().is2xxSuccessful(),
                        MockMvcResultMatchers.view().name("university/audiences/editAudience"),
                        MockMvcResultMatchers.model().attributeExists("audience"),
                        MockMvcResultMatchers.model().attribute("audience", audience)
                );

    }
    @Test
    void updateAudience() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.patch("/audiences/1"))
                .andExpectAll(
                        MockMvcResultMatchers.status().is3xxRedirection(),
                        MockMvcResultMatchers.redirectedUrl("/audience/1")
                );
    }

    @Test
    void deleteAudience() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/audiences/1"))
                .andExpectAll(
                        MockMvcResultMatchers.status().is3xxRedirection(),
                        MockMvcResultMatchers.redirectedUrl("/audiences")
                );
    }

}