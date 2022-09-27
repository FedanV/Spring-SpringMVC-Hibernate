package com.foxminded.vitaliifedan.task10.controllers;

import com.foxminded.vitaliifedan.task10.exceptions.GroupException;
import com.foxminded.vitaliifedan.task10.models.groups.Group;
import com.foxminded.vitaliifedan.task10.models.schedules.Course;
import com.foxminded.vitaliifedan.task10.services.GroupService;
import com.foxminded.vitaliifedan.task10.services.validators.GroupValidationService;
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


@WebMvcTest(GroupController.class)
class GroupControllerTest {
    @MockBean
    private GroupService groupService;
    @MockBean
    private GroupValidationService groupValidationService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void getGroups() throws Exception {
        Group group = new Group("Group1");
        Mockito.doReturn(List.of(group)).when(groupService).findAll();
        mockMvc.perform(MockMvcRequestBuilders.get("/groups"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.view().name("university/groups/allGroups"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("groups"))
                .andExpect(MockMvcResultMatchers.model().attribute("groups", List.of(group)));

    }

    @Test
    void addGroup() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/groups/add"))
                .andExpectAll(
                        MockMvcResultMatchers.status().is2xxSuccessful(),
                        MockMvcResultMatchers.view().name("university/groups/addGroup"),
                        MockMvcResultMatchers.model().attributeExists("group"),
                        MockMvcResultMatchers.model().attribute("group", new Group())
                );
    }

    @Test
    void saveGroup() throws Exception {
        Mockito.doReturn("").when(groupValidationService).validateGroupName(Mockito.anyString());
        mockMvc.perform(MockMvcRequestBuilders.post("/groups/addGroup")
                        .param("groupName", "group1"))
                .andExpectAll(
                        MockMvcResultMatchers.status().is3xxRedirection(),
                        MockMvcResultMatchers.redirectedUrl("/groups")
                );
    }

    @Test
    void showGroup() throws Exception {
        Group group = new Group();
        Mockito.doReturn(Optional.of(group)).when(groupService).findById(Mockito.anyInt());
        mockMvc.perform(MockMvcRequestBuilders.get("/groups/1/edit"))
                .andExpectAll(
                        MockMvcResultMatchers.status().is2xxSuccessful(),
                        MockMvcResultMatchers.view().name("university/groups/editGroup"),
                        MockMvcResultMatchers.model().attributeExists("group"),
                        MockMvcResultMatchers.model().attribute("group", group)
                );

    }

    @Test
    void updateGroup() throws Exception {
        Mockito.doReturn("").when(groupValidationService).validateGroupName("test");
        mockMvc.perform(MockMvcRequestBuilders.post("/groups/1")
                        .param("groupName", "test"))
                .andExpectAll(
                        MockMvcResultMatchers.status().is3xxRedirection(),
                        MockMvcResultMatchers.redirectedUrl("/groups/1")
                );
    }

    @Test
    void deleteGroup() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/groups/1"))
                .andExpectAll(
                        MockMvcResultMatchers.status().is3xxRedirection(),
                        MockMvcResultMatchers.redirectedUrl("/groups")
                );
    }

    @Test
    void checkGroupExceptionWhenCreateGroup() throws Exception {
        Mockito.doThrow(GroupException.class).when(groupService).create(new Group("test"));
        Mockito.doReturn("").when(groupValidationService).validateGroupName("test");
        mockMvc.perform(MockMvcRequestBuilders.post("/groups/addGroup")
                .param("groupName", "test"))
                .andExpectAll(
                        MockMvcResultMatchers.status().is2xxSuccessful(),
                        MockMvcResultMatchers.view().name("university/error")
                );
    }

    @Test
    void checkGroupExceptionWhenUpdateGroup() throws Exception {
        Mockito.doThrow(GroupException.class).when(groupService).update(new Group(1, "test"));
        Mockito.doReturn("").when(groupValidationService).validateGroupName("test");
        mockMvc.perform(MockMvcRequestBuilders.post("/groups/1")
                        .param("groupName", "test"))
                .andExpectAll(
                        MockMvcResultMatchers.status().is2xxSuccessful(),
                        MockMvcResultMatchers.view().name("university/error")
                );
    }

    @Test
    void checkGroupExceptionWhenDeleteGroup() throws Exception {
        Mockito.doThrow(GroupException.class).when(groupService).deleteById(1);
        mockMvc.perform(MockMvcRequestBuilders.delete("/groups/1"))
                .andExpectAll(
                        MockMvcResultMatchers.status().is2xxSuccessful(),
                        MockMvcResultMatchers.view().name("university/error")
                );
    }

    @Test
    void checkGroupNameWhenCreateGroup() throws Exception {
        Mockito.doReturn("error").when(groupValidationService).validateGroupName("test");
        mockMvc.perform(MockMvcRequestBuilders.post("/groups/addGroup")
                        .param("groupName", "test"))
                .andExpectAll(
                        MockMvcResultMatchers.status().is2xxSuccessful(),
                        MockMvcResultMatchers.view().name("university/groups/addGroup"),
                        MockMvcResultMatchers.model().attributeExists("group")
                );
    }

    @Test
    void checkGroupNameWhenUpdateGroup() throws Exception {
        Mockito.doReturn("error").when(groupValidationService).validateGroupName("test");
        Mockito.doReturn(Optional.of(new Group(2))).when(groupService).findGroupByName(Mockito.anyString());
        Mockito.doReturn(Optional.of(new Group(2))).when(groupService).findGroupByName(Mockito.anyString());
        mockMvc.perform(MockMvcRequestBuilders.post("/groups/1")
                        .param("groupName", "test"))
                .andExpectAll(
                        MockMvcResultMatchers.status().is2xxSuccessful(),
                        MockMvcResultMatchers.view().name("university/groups/editGroup"),
                        MockMvcResultMatchers.model().attributeExists("group")
                );
    }
}