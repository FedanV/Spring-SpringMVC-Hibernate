package com.foxminded.vitaliifedan.task10.models.schedules;

import com.foxminded.vitaliifedan.task10.models.IntegerId;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Audience extends IntegerId {

    private Integer roomNumber;

    public Audience(Integer id) {
        super(id);
    }

    public Audience(Integer id, Integer roomNumber) {
        super(id);
        this.roomNumber = roomNumber;
    }

    public Audience() {
    }
}
