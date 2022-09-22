package com.foxminded.vitaliifedan.task10.controllers;

import com.foxminded.vitaliifedan.task10.models.groups.Group;
import com.foxminded.vitaliifedan.task10.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/groups")
public class GroupController {

    private final GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping()
    public String getGroups(Model model) {
        List<Group> groups = groupService.findAll();
        model.addAttribute("groups", groups);
        return "university/group";
    }
}
