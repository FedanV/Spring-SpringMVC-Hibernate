package com.foxminded.vitaliifedan.task10.models.persons;


import com.foxminded.vitaliifedan.task10.models.BaseEntity;
import com.foxminded.vitaliifedan.task10.models.schedules.Course;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "teacher_course")
public class TeacherCourse implements BaseEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ToString.Exclude
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    @ToString.Exclude
    private Course course;

    public void setUser(User user) {
        this.user = user;
        this.user.getTeacherCourses().add(this);
    }

    public void setCourse(Course course) {
        this.course = course;
        this.course.getTeacherCourses().add(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TeacherCourse that = (TeacherCourse) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
