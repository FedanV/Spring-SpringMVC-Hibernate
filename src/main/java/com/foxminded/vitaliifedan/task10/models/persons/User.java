package com.foxminded.vitaliifedan.task10.models.persons;

import com.foxminded.vitaliifedan.task10.models.BaseEntity;
import com.foxminded.vitaliifedan.task10.models.schedules.Lecture;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "users")
public class User implements BaseEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Size(min = 3, max = 30, message = "Name should contain 3 to 30 characters")
    @Column(name = "name")
    private String name;

    @Size(min = 3, max = 30, message = "Surname should contain 3 to 30 characters")
    @Column(name = "surname")
    private String surname;

    @Size(min = 6, max = 14, message = "Phone should contain 6 to 14 digits")
    @Column(name = "phone", unique = true)
    private String phone;

    @NotEmpty(message = "Login should not be empty!")
    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    @OneToOne(mappedBy = "user")
    private StudentGroup studentGroup;

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    @Builder.Default
    private List<TeacherCourse> teacherCourses = new ArrayList<>();

    @OneToMany(mappedBy = "teacher", fetch = FetchType.LAZY)
    @ToString.Exclude
    @Builder.Default
    private List<Lecture> lectures = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(surname, user.surname) && Objects.equals(phone, user.phone) && Objects.equals(login, user.login) && Objects.equals(password, user.password) && role == user.role && userType == user.userType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, phone, login, password, role, userType);
    }
}
