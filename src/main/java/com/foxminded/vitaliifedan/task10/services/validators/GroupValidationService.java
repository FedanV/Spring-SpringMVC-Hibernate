package com.foxminded.vitaliifedan.task10.services.validators;

import com.foxminded.vitaliifedan.task10.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupValidationService {

    private final GroupService groupService;
    @Autowired
    public GroupValidationService(GroupService groupService) {
        this.groupService = groupService;
    }

    public String validateGroupName(String name) {
        String message = "";
        if(groupService.findGroupByName(name).isPresent()) {
            message = String.format("Group %s exists", name);
        }
        return message;
    }
}
