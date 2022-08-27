package com.foxminded.vitaliifedan.task10.models.groups;

import com.foxminded.vitaliifedan.task10.models.persons.Student;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Group {

    int id;
    private String groupName;
    private List<Student> students;

    public Group(String groupName, List<Student> students) {
        this.groupName = groupName;
        this.students = students;
    }
}
