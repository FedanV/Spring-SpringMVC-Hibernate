package com.foxminded.vitaliifedan.task10.controllers;

import com.foxminded.vitaliifedan.task10.models.schedules.Audience;
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
                .andExpect(MockMvcResultMatchers.view().name("university/audience"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("audiences"))
                .andExpect(MockMvcResultMatchers.model().attribute("audiences", List.of(audience)));
    }

}