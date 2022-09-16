package com.foxminded.vitaliifedan.task10.services.impl;

import com.foxminded.vitaliifedan.task10.dao.AudienceDao;
import com.foxminded.vitaliifedan.task10.exceptions.AudienceException;
import com.foxminded.vitaliifedan.task10.models.schedules.Audience;
import com.foxminded.vitaliifedan.task10.services.AudienceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AudienceServiceImpl implements AudienceService {

    private static final Logger logger = LoggerFactory.getLogger(AudienceServiceImpl.class);

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
        try {
            return audienceDao.save(audience);
        } catch (AudienceException e) {
            logger.error("Exception happened when Audience is creating", e);
            throw e;
        }
    }

    @Transactional
    public Audience update(Audience audience) {
        try {
            return audienceDao.save(audience);
        } catch (AudienceException e) {
            logger.error("Exception happened when Audience is updating ", e);
            throw e;
        }
    }

    @Transactional
    public Boolean deletedById(Integer id) {
        try {
            return audienceDao.delete(id);
        } catch (AudienceException e) {
            logger.error("Exception happened when Audience is deleting", e);
            throw e;
        }
    }
}
