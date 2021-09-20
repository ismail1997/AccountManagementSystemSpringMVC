package com.ismail.accountsystemspringmvc.controllers;

import com.ismail.accountsystemspringmvc.entities.Reclamation;
import com.ismail.accountsystemspringmvc.services.IReclamationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ReclamationController {
    @Autowired
    private IReclamationService reclamationService;
    @GetMapping(path = "/reclamations")
    public String getReclamations(Model model){
        List<Reclamation> reclamations = reclamationService.getReclamations();
        model.addAttribute("reclamations",reclamations);
        return "reclamations";
    }
}
