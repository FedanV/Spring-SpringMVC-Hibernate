package com.foxminded.vitaliifedan.task10.services.impl;

import com.foxminded.vitaliifedan.task10.dao.AudienceDao;
import com.foxminded.vitaliifedan.task10.models.schedules.Audience;
import com.foxminded.vitaliifedan.task10.services.AudienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AudienceServiceImpl implements AudienceService {

    private final AudienceDao audienceDao;

    @Autowired
    public AudienceServiceImpl(AudienceDao audienceDao) {
        this.audienceDao = audienceDao;
    }

    @Transactional(readOnly = true)
    public List<Audience> findAll() {
        return audienceDao.getAll();
    }

    @Transactional(readOnly = true)
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
