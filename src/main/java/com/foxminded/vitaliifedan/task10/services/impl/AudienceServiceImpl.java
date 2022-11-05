package com.foxminded.vitaliifedan.task10.services.impl;

import com.foxminded.vitaliifedan.task10.models.schedules.Audience;
import com.foxminded.vitaliifedan.task10.repositories.AudienceRepository;
import com.foxminded.vitaliifedan.task10.services.AudienceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AudienceServiceImpl implements AudienceService {

    private static final Logger logger = LoggerFactory.getLogger(AudienceServiceImpl.class);

    private final AudienceRepository audienceRepository;

    @Autowired
    public AudienceServiceImpl(AudienceRepository audienceRepository) {
        this.audienceRepository = audienceRepository;
    }

    @Transactional(readOnly = true)
    public List<Audience> findAll() {
        return audienceRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Audience> findById(Integer id) {
        return audienceRepository.findById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    public Audience create(Audience audience) {
        return audienceRepository.save(audience);
    }

    @Transactional
    public Audience update(Audience audience) {
        return audienceRepository.save(audience);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    public void deletedById(Integer id) {
        audienceRepository.deleteById(id);

    }

    @Override
    public Optional<Audience> findAudienceByNumber(Integer number) {
        return audienceRepository.findAudienceByRoomNumber(number);
    }

}
