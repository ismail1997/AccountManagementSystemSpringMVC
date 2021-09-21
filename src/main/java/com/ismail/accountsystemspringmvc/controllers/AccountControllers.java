package com.ismail.accountsystemspringmvc.controllers;

import com.ismail.accountsystemspringmvc.entities.Account;
import com.ismail.accountsystemspringmvc.services.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AccountControllers {
    @Autowired
    private IAccountService accountService;

    @GetMapping(path = "/accounts")
    public String getAccounts(Model model){
        List<Account> accounts = accountService.getAccounts();
        model.addAttribute("accounts",accounts);
        return "accounts";
    }

    @GetMapping(path = "/accountDetail")
    public String getOneAccount(Long id,Model model){
        Account account = accountService.getOneAccount(id);
        model.addAttribute("account",account);
        return "account";
    }
}
