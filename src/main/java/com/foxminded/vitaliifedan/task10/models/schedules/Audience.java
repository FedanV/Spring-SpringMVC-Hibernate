package com.foxminded.vitaliifedan.task10.models.schedules;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Audience {
    private int roomNumber;

    public Audience() {
    }

    public Audience(int roomNumber) {
        this.roomNumber = roomNumber;
    }

}
