package com.foxminded.vitaliifedan.task10.controllers;

import com.foxminded.vitaliifedan.task10.models.schedules.Audience;
import com.foxminded.vitaliifedan.task10.services.AudienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        return "university/audiences/allAudiences";
    }

    @GetMapping("/add")
    public String addAudience(@ModelAttribute Audience audience) {
        return "university/audiences/addAudience";
    }

    @PostMapping("/addAudience")
    public String saveAudience(@ModelAttribute Audience audience) {
        audienceService.create(audience);
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

    @PatchMapping("/{id}")
    public String updateAudience(@PathVariable("id") Integer id, @ModelAttribute("audience") Audience audience) {
        audienceService.update(audience);
        return "redirect:/audience/" + id;
    }

    @DeleteMapping("/{id}")
    public String deleteAudience(@PathVariable("id") Integer id) {
        audienceService.deletedById(id);
        return "redirect:/audiences";
    }
}
