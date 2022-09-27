package com.foxminded.vitaliifedan.task10.services.validators;

import com.foxminded.vitaliifedan.task10.dao.GroupDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupValidationService {

    private final GroupDao groupDao;
    @Autowired
    public GroupValidationService(GroupDao groupDao) {
        this.groupDao = groupDao;
    }

    public String validateGroupName(String name) {
        String message = "";
        if(groupDao.findGroupByGroupName(name).isPresent()) {
            message = String.format("Group %s exists", name);
        }
        return message;
    }
}
