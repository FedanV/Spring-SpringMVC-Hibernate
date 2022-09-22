package com.foxminded.vitaliifedan.task10.controllers;

import com.foxminded.vitaliifedan.task10.models.schedules.Audience;
import com.foxminded.vitaliifedan.task10.services.AudienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/audiences")
public class AudienceController {

    private final AudienceService audienceService;

    @Autowired
    public AudienceController(AudienceService audienceService) {
        this.audienceService = audienceService;
    }

    @GetMapping()
    public String getAudiences(Model model) {
        List<Audience> audiences = audienceService.findAll();
        model.addAttribute("audiences", audiences);
        return "university/audience";
    }
}
