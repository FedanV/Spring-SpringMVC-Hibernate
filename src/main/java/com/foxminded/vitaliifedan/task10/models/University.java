package com.foxminded.vitaliifedan.task10.models;

import com.foxminded.vitaliifedan.task10.models.schedules.Audience;
import com.foxminded.vitaliifedan.task10.models.groups.Group;
import com.foxminded.vitaliifedan.task10.models.schedules.SemesterTimetable;
import com.foxminded.vitaliifedan.task10.models.persons.Student;
import com.foxminded.vitaliifedan.task10.models.persons.Teacher;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class University {

    private List<Student> students;
    private List<Group> groups;
    private List<Audience> audiences;
    private List<Teacher> teachers;
    private List<SemesterTimetable> semesterTimetables;

    public University() {
    }

    public University(List<Student> students, List<Group> groups, List<Audience> audiences, List<Teacher> teachers, List<SemesterTimetable> semesterTimetables) {
        this.students = students;
        this.groups = groups;
        this.audiences = audiences;
        this.teachers = teachers;
        this.semesterTimetables = semesterTimetables;
    }
}
