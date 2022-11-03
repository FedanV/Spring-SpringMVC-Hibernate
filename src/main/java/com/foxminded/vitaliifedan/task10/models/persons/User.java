package com.foxminded.vitaliifedan.task10.models.persons;

import com.foxminded.vitaliifedan.task10.models.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SuperBuilder
@Table(name = "users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_type", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("USER")
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

    @Column(name = "user_type", insertable = false, updatable = false)
    protected String userType;

}
