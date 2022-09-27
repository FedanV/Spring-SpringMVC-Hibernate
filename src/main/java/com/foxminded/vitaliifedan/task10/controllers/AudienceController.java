package com.foxminded.vitaliifedan.task10.controllers;

import com.foxminded.vitaliifedan.task10.exceptions.AudienceException;
import com.foxminded.vitaliifedan.task10.models.schedules.Audience;
import com.foxminded.vitaliifedan.task10.services.AudienceService;
import com.foxminded.vitaliifedan.task10.services.validators.AudienceValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/audiences")
public class AudienceController {

    private final AudienceService audienceService;
    private final AudienceValidationService audienceValidationService;

    @Autowired
    public AudienceController(AudienceService audienceService, AudienceValidationService audienceValidationService) {
        this.audienceService = audienceService;
        this.audienceValidationService = audienceValidationService;
    }

    @GetMapping()
    public String getAudiences(Model model) {
        List<Audience> audiences = audienceService.findAll();
        model.addAttribute("audiences", audiences);
        return "university/audiences/allAudiences";
    }

    @GetMapping("/add")
    public String addAudience(@ModelAttribute Audience audience) {
        return "university/audiences/addAudience";
    }

    @PostMapping("/addAudience")
    public String saveAudience(@ModelAttribute @Valid Audience audience, BindingResult result) {
        String err = audienceValidationService.validateRoomNumber(audience.getRoomNumber());
        if(!err.isEmpty()) {
            result.rejectValue("roomNumber", "", err);
        }
        if(result.hasErrors()) {
            return "university/audiences/addAudience";
        }
        try {
            audienceService.create(audience);
        } catch (AudienceException e) {
            return "university/error";
        }
        return "redirect:/audiences";
    }

    @GetMapping("/{id}")
    public String showAudience(@PathVariable("id") Integer id, Model model) {
        audienceService.findById(id).ifPresent(entity -> model.addAttribute("audience", entity));
        return "university/audiences/showAudience";
    }

    @GetMapping("/{id}/edit")
    public String editAudience(@PathVariable("id") Integer id, Model model) {
        audienceService.findById(id).ifPresent(entity -> model.addAttribute("audience", entity));
        return "university/audiences/editAudience";
    }

    @PostMapping ("/{id}")
    public String updateAudience(@PathVariable("id") Integer id, @ModelAttribute("audience") Audience audience, BindingResult result) {
        if (audienceService.findAudienceByNumber(audience.getRoomNumber()).isPresent() &&
                !audienceService.findAudienceByNumber(audience.getRoomNumber()).get().getId().equals(id)) {
            result.rejectValue("roomNumber", "", audienceValidationService.validateRoomNumber(audience.getRoomNumber()));
        }
        if(result.hasErrors()) {
            return "university/audiences/editAudience";
        }
        try {
            audienceService.update(audience);
        } catch (AudienceException e) {
            return "university/error";
        }
        return "redirect:/audiences/" + id;
    }

    @DeleteMapping("/{id}")
    public String deleteAudience(@PathVariable("id") Integer id) {
        try {
            audienceService.deletedById(id);
        } catch (AudienceException e) {
            return "university/error";
        }
        return "redirect:/audiences";
    }
}
