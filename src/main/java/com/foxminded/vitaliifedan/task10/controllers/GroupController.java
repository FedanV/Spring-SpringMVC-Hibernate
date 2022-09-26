package com.foxminded.vitaliifedan.task10.controllers;

import com.foxminded.vitaliifedan.task10.models.groups.Group;
import com.foxminded.vitaliifedan.task10.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        return "university/groups/allGroups";
    }

    @GetMapping("/add")
    public String addGroup(@ModelAttribute("group") Group group) {
        return "university/groups/addGroup";
    }

    @PostMapping("/addGroup")
    public String saveGroup(@ModelAttribute Group group) {
        groupService.create(group);
        return "redirect:/groups";
    }

    @GetMapping("/{id}")
    public String showGroup(@PathVariable("id") Integer id, Model model) {
        groupService.findById(id).ifPresent(entity -> model.addAttribute("group", entity));
        return "university/groups/showGroup";
    }

    @GetMapping("/{id}/edit")
    public String editGroup(@PathVariable("id") Integer id, Model model) {
        groupService.findById(id).ifPresent(entity -> model.addAttribute("group", entity));
        return "university/groups/editGroup";
    }

    @PatchMapping("/{id}")
    public String updateGroup(@PathVariable("id") Integer id, @ModelAttribute("group") Group group) {
        groupService.update(group);
        return "redirect:/groups/" + id;
    }

    @DeleteMapping("/{id}")
    public String deleteGroup(@PathVariable("id") Integer id) {
        groupService.deleteById(id);
        return "redirect:/groups";
    }
}
