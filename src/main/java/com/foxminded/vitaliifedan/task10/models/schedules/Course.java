package com.foxminded.vitaliifedan.task10.models.schedules;

import com.foxminded.vitaliifedan.task10.models.BaseEntity;
import com.foxminded.vitaliifedan.task10.models.persons.Teacher;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "course")
public class Course implements BaseEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotEmpty(message = "Name can't be empty")
    @Size(min = 3, max = 30, message = "Course should contains 3 to 30 symbols")
    @Column(name = "course_name", unique = true, nullable = false)
    private String courseName;

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
    @ToString.Exclude
    @Builder.Default
    private List<Lecture> lectures = new ArrayList<>();

    @OneToMany(mappedBy = "course")
    @ToString.Exclude
    private List<Teacher> teachers = new ArrayList<>();

}
