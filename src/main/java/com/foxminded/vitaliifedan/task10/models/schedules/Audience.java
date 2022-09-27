package com.foxminded.vitaliifedan.task10.models.schedules;

import com.foxminded.vitaliifedan.task10.models.IntegerId;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class Audience extends IntegerId {
    @NotNull(message = "Room number can't be null")
    @Min(value = 1, message = "Min value is 1")
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
