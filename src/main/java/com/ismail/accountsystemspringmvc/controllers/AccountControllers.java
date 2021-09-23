package com.ismail.accountsystemspringmvc.controllers;

import com.ismail.accountsystemspringmvc.entities.Account;
import com.ismail.accountsystemspringmvc.services.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AccountControllers {
    @Autowired
    private IAccountService accountService;

    @GetMapping(path = "/accounts")
    public String getAccounts(Model model,@RequestParam(name = "email",defaultValue = "") String email,
                              @RequestParam(name="page",defaultValue = "0") int page){
        Page<Account> pageOfAccounts =accountService.getAccountsByUserEmail(email,page,6);
        List<Account> accounts = pageOfAccounts.getContent();
        model.addAttribute("accounts",accounts);
        model.addAttribute("pages",new int[pageOfAccounts.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("email",email);
        return "accounts";
    }

    @GetMapping(path = "/accountDetail")
    public String getOneAccount(Long id,Model model){
        Account account = accountService.getOneAccount(id);
        model.addAttribute("account",account);
        return "account";
    }

    @GetMapping(path = "/deleteAccount")
    public String deleteAccount(Long id){
        accountService.deleteAccount(id);
        return "redirect:/accounts";
    }

    @GetMapping(path = "/withdrawForm")
    public String withdrawMoney(Long id,Model model){
        Account account=accountService.getOneAccount(id);
        model.addAttribute("account",account);
        return "withdrawform";
    }

//    @GetMapping(path = "/abc")
//    public String getAccounts2(Model model,@RequestParam(name = "email",defaultValue = "") String email,
//                              @RequestParam(name="page",defaultValue = "0") int page){
//        Page<Account> pageOfAccounts =accountService.getAccountsByUserEmail(email,page,6);
//        List<Account> accounts = pageOfAccounts.getContent();
//        model.addAttribute("accounts",accounts);
//        model.addAttribute("pages",new int[pageOfAccounts.getTotalPages()]);
//        model.addAttribute("currentPage",page);
//        model.addAttribute("email",email);
//        return "abc";
//    }
}
