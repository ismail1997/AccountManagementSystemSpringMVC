package com.ismail.accountsystemspringmvc.controllers;

import com.ismail.accountsystemspringmvc.entities.Operation;
import com.ismail.accountsystemspringmvc.services.IOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class OperationController {
    @Autowired
    private IOperationService operationService;

    @GetMapping(path = "/operations")
    public String operations(Model model){
        List<Operation> operations = operationService.getOperations();
        model.addAttribute("operations",operations);
        return "operations";
    }
}
