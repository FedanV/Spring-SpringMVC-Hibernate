package com.foxminded.vitaliifedan.task10.services.validators;

import com.foxminded.vitaliifedan.task10.services.AudienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AudienceValidationService {

    private final AudienceService audienceService;

    @Autowired
    public AudienceValidationService( AudienceService audienceService) {
        this.audienceService = audienceService;
    }

    public String validateRoomNumber(Integer roomNumber) {
        String message = "";
        if(audienceService.findAudienceByNumber(roomNumber).isPresent()) {
            message = String.format("Room number %s exists", roomNumber);
        }
        return message;
    }
}
