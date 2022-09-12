package com.foxminded.vitaliifedan.task10.services;

import com.foxminded.vitaliifedan.task10.dao.AudienceDao;
import com.foxminded.vitaliifedan.task10.models.schedules.Audience;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class AudienceService {

    private final AudienceDao audienceDao;

    @Autowired
    public AudienceService(AudienceDao audienceDao) {
        this.audienceDao = audienceDao;
    }

    public List<Audience> findAll() {
        return audienceDao.getAll();
    }

    public Optional<Audience> findById(Integer id) {
        return audienceDao.getById(id);
    }

    @Transactional
    public Audience create(Audience audience) {
        return audienceDao.save(audience);
    }

    @Transactional
    public Audience update(Audience audience) {
        return audienceDao.save(audience);
    }

    @Transactional
    public Boolean deletedById(Integer id) {
        return audienceDao.delete(id);
    }
}
