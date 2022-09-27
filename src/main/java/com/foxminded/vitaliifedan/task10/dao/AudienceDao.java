package com.foxminded.vitaliifedan.task10.dao;

import com.foxminded.vitaliifedan.task10.models.schedules.Audience;

import java.util.Optional;

public interface AudienceDao extends CrudDao<Audience ,Integer> {

    Optional<Audience> findAudienceByRoomNumber(Integer roomNumber);

}
