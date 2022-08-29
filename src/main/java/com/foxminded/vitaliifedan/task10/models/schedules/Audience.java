package com.foxminded.vitaliifedan.task10.models.schedules;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Audience {

    int id;
    private Integer roomNumber;

    public Audience() {
    }

    public Audience(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Audience(int id, Integer roomNumber) {
        this.id = id;
        this.roomNumber = roomNumber;
    }




}
