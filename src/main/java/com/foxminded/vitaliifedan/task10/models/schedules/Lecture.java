package com.foxminded.vitaliifedan.task10.models.schedules;

import com.foxminded.vitaliifedan.task10.models.BaseEntity;
import com.foxminded.vitaliifedan.task10.models.groups.Group;
import com.foxminded.vitaliifedan.task10.models.persons.User;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "lecture")
public class Lecture implements BaseEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "pair_number")
    private Integer pairNumber;

    @Column(name = "lecture_date")
    private LocalDate lectureDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    @ToString.Exclude
    private Course course;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id", referencedColumnName = "id")
    @ToString.Exclude
    private User teacher;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id", referencedColumnName = "id")
    @ToString.Exclude
    private Group group;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "audience_id", referencedColumnName = "id")
    @ToString.Exclude
    private Audience audience;

    public void setAudience(Audience audience) {
        this.audience = audience;
        this.audience.getLectures().add(this);
    }

    public void setGroup(Group group) {
        this.group = group;
        this.group.getLectures().add(this);
    }

    public void setTeacher(User teacher) {
        this.teacher = teacher;
        this.teacher.getLectures().add(this);
    }

    public void setCourse(Course course) {
        this.course = course;
        this.course.getLectures().add(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Lecture lecture = (Lecture) o;
        return id != null && Objects.equals(id, lecture.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
