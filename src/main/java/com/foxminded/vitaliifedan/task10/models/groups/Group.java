package com.foxminded.vitaliifedan.task10.models.groups;

import com.foxminded.vitaliifedan.task10.models.IntegerId;
import com.foxminded.vitaliifedan.task10.models.persons.Student;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
public class Group extends IntegerId {

    @NotEmpty(message = "Can't be empty")
    @Size(min=3, max = 30, message = "Group name should have size 3 to 30")
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
