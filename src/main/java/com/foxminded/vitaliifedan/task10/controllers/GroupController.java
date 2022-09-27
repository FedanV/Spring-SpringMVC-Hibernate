package com.foxminded.vitaliifedan.task10.controllers;

import com.foxminded.vitaliifedan.task10.exceptions.GroupException;
import com.foxminded.vitaliifedan.task10.models.groups.Group;
import com.foxminded.vitaliifedan.task10.services.GroupService;
import com.foxminded.vitaliifedan.task10.services.validators.GroupValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/groups")
public class GroupController {

    private final GroupService groupService;
    private final GroupValidationService groupValidationService;

    @Autowired
    public GroupController(GroupService groupService, GroupValidationService groupValidationService) {
        this.groupService = groupService;
        this.groupValidationService = groupValidationService;
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
    public String saveGroup(@ModelAttribute @Valid Group group, BindingResult result) {
        String err = groupValidationService.validateGroupName(group.getGroupName());
        if (!err.isEmpty()) {
            result.rejectValue("groupName", "", err);
        }
        if (result.hasErrors()) {
            return "university/groups/addGroup";
        }
        try {
            groupService.create(group);
        } catch (GroupException e) {
            return "university/error";
        }
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

    @PostMapping("/{id}")
    public String updateGroup(@PathVariable("id") Integer id, @ModelAttribute("group") @Valid Group group, BindingResult result) {
        if (groupService.findGroupByName(group.getGroupName()).isPresent() &&
                !groupService.findGroupByName(group.getGroupName()).get().getId().equals(id)) {
            result.rejectValue("groupName", "", groupValidationService.validateGroupName(group.getGroupName()));
        }
        if (result.hasErrors()) {
            return "university/groups/editGroup";
        }
        try {
            groupService.update(group);
        } catch (GroupException e) {
            return "university/error";
        }
        return "redirect:/groups/" + id;
    }

    @DeleteMapping("/{id}")
    public String deleteGroup(@PathVariable("id") Integer id) {
        try {
            groupService.deleteById(id);
        } catch (GroupException e) {
            return "university/error";
        }
        return "redirect:/groups";
    }
}
