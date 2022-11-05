package com.foxminded.vitaliifedan.task10.services;

import com.foxminded.vitaliifedan.task10.models.schedules.Audience;

import java.util.List;
import java.util.Optional;

public interface AudienceService {

    List<Audience> findAll();

    Optional<Audience> findById(Integer id);

    Audience create(Audience audience);

    Audience update(Audience audience);

    void deletedById(Integer id);
    Optional<Audience> findAudienceByNumber(Integer number);
}
