package com.foxminded.vitaliifedan.task10.models.schedules;

import com.foxminded.vitaliifedan.task10.models.BaseEntity;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "audience")
public class Audience implements BaseEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotNull(message = "Room number can't be null")
    @Min(value = 1, message = "Min value is 1")
    @Column(name = "room_number")
    private Integer roomNumber;

    @OneToMany(mappedBy = "audience", fetch = FetchType.LAZY)
    @ToString.Exclude
    @Builder.Default
    private List<Lecture> lectures = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Audience audience = (Audience) o;
        return id != null && Objects.equals(id, audience.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
