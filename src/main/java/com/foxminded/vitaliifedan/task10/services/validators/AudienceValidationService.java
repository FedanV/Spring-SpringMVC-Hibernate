package com.foxminded.vitaliifedan.task10.services.validators;

import com.foxminded.vitaliifedan.task10.dao.AudienceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AudienceValidationService {

    private final AudienceDao audienceDao;

    @Autowired
    public AudienceValidationService(AudienceDao audienceDao) {
        this.audienceDao = audienceDao;
    }

    public String validateRoomNumber(Integer roomNumber) {
        String message = "";
        if(audienceDao.findAudienceByRoomNumber(roomNumber).isPresent()) {
            message = String.format("Room number %s exists", roomNumber);
        }
        return message;
    }
}
