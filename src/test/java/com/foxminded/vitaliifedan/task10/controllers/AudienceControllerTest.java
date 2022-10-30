package com.foxminded.vitaliifedan.task10.controllers;

import com.foxminded.vitaliifedan.task10.exceptions.AudienceException;
import com.foxminded.vitaliifedan.task10.models.schedules.Audience;
import com.foxminded.vitaliifedan.task10.services.AudienceService;
import com.foxminded.vitaliifedan.task10.services.validators.AudienceValidationService;
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


@WebMvcTest(AudienceController.class)
@WithMockUser(username = "test", password = "test", authorities = {"ADMIN"})
class AudienceControllerTest {

    @MockBean
    private AudienceService audienceService;
    @MockBean
    private AudienceValidationService audienceValidationService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAudiences() throws Exception {
        Audience audience = Audience.builder().id(10).build();
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
                        MockMvcResultMatchers.model().attributeExists("audience")
                );
    }

    @Test
    void saveAudience() throws Exception {
        Mockito.doReturn("").when(audienceValidationService).validateRoomNumber(Mockito.anyInt());
        mockMvc.perform(MockMvcRequestBuilders.post("/audiences/addAudience")
                        .param("roomNumber", "1"))
                .andExpectAll(
                        MockMvcResultMatchers.status().is3xxRedirection(),
                        MockMvcResultMatchers.redirectedUrl("/audiences")
                );
    }

    @Test
    void showAudience() throws Exception {
        Audience audience = Audience.builder().build();
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
        Mockito.doReturn("").when(audienceValidationService).validateRoomNumber(Mockito.anyInt());
        mockMvc.perform(MockMvcRequestBuilders.post("/audiences/1")
                        .param("roomNumber", "1"))
                .andExpectAll(
                        MockMvcResultMatchers.status().is3xxRedirection(),
                        MockMvcResultMatchers.redirectedUrl("/audiences/1")
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


    @Test
    void checkAudienceExceptionWhenCreateAudience() throws Exception {
        Mockito.doThrow(AudienceException.class).when(audienceService).create(Mockito.any(Audience.class));
        Mockito.doReturn("").when(audienceValidationService).validateRoomNumber(Mockito.anyInt());
        mockMvc.perform((MockMvcRequestBuilders.post("/audiences/addAudience"))
                .param("roomNumber", "1")
        ).andExpectAll(
                MockMvcResultMatchers.status().is2xxSuccessful(),
                MockMvcResultMatchers.view().name("university/error")
        );
    }

    @Test
    void checkAudienceExceptionWhenUpdateAudience() throws Exception {
        Mockito.doThrow(AudienceException.class).when(audienceService).update(
                Audience.builder()
                        .id(1)
                        .roomNumber(1)
                        .build());
        Mockito.doReturn("").when(audienceValidationService).validateRoomNumber(Mockito.anyInt());
        mockMvc.perform((MockMvcRequestBuilders.post("/audiences/1"))
                .param("roomNumber", "1")
        ).andExpectAll(
                MockMvcResultMatchers.status().is2xxSuccessful(),
                MockMvcResultMatchers.view().name("university/error")
        );
    }

    @Test
    void checkAudienceExceptionWhenDeleteAudience() throws Exception {
        Mockito.doThrow(AudienceException.class).when(audienceService).deletedById(1);
        mockMvc.perform((MockMvcRequestBuilders.delete("/audiences/1"))
        ).andExpectAll(
                MockMvcResultMatchers.status().is2xxSuccessful(),
                MockMvcResultMatchers.view().name("university/error")
        );
    }

    @Test
    void checkRoomNumberWhenCreateAudience() throws Exception {
        Mockito.doReturn("Error message").when(audienceValidationService).validateRoomNumber(Mockito.anyInt());
        mockMvc.perform((MockMvcRequestBuilders.post("/audiences/addAudience"))
                .param("roomNumber", "1")
        ).andExpectAll(
                MockMvcResultMatchers.status().is2xxSuccessful(),
                MockMvcResultMatchers.view().name("university/audiences/addAudience"),
                MockMvcResultMatchers.model().attributeExists("audience")
        );
    }

}