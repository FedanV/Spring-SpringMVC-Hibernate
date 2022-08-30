package com.foxminded.vitaliifedan.task10.models.groups;

import com.foxminded.vitaliifedan.task10.models.IntegerId;
import com.foxminded.vitaliifedan.task10.models.persons.Student;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Group extends IntegerId {
    private String groupName;
    private List<Student> students;

    public Group() {
    }

    public Group(String groupName) {
        this.groupName = groupName;
    }

    public Group(Integer id) {
        super(id);
    }

    public Group(Integer id, String groupName) {
        super(id);
        this.groupName = groupName;
    }
}
