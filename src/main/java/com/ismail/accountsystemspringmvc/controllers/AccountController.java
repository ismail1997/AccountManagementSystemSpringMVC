package com.ismail.accountsystemspringmvc.controllers;

import com.ismail.accountsystemspringmvc.entities.Account;
import com.ismail.accountsystemspringmvc.entities.models.DepositingModel;
import com.ismail.accountsystemspringmvc.entities.models.WithdrawingModel;
import com.ismail.accountsystemspringmvc.services.IAccountService;
import com.ismail.accountsystemspringmvc.services.IDepositingService;
import com.ismail.accountsystemspringmvc.services.IWithdrawingService;
import com.ismail.accountsystemspringmvc.utils.EncryptionTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AccountController {
    @Autowired
    private IAccountService accountService;

    @Autowired
    private IWithdrawingService withdrawingService;

    @Autowired
    private IDepositingService depositingService;

//    @GetMapping(path = "/accounts")
//    public String getAccounts(Model model,@RequestParam(name = "email",defaultValue = "") String email,
//                              @RequestParam(name="page",defaultValue = "0") int page){
//        Page<Account> pageOfAccounts =accountService.getAccountsByUserEmail(email,page,6);
//        List<Account> accounts = pageOfAccounts.getContent();
//        model.addAttribute("accounts",accounts);
//        model.addAttribute("pages",new int[pageOfAccounts.getTotalPages()]);
//        model.addAttribute("currentPage",page);
//        model.addAttribute("email",email);
////        String encryptedID = EncryptionTool.encrypt(account.getId()+"");
////        model.addAttribute("accountID",account);
//        return "accounts";
//    }
    @GetMapping(path = "/accounts")
    public String getAcc(Model model,@RequestParam(name = "email",defaultValue = "") String email,
                         @RequestParam(name="page",defaultValue = "0") int page){
        Page<Account> pageOfAccounts =accountService.getAccountsByUserEmail(email,page,4);
        List<Account> accounts = pageOfAccounts.getContent();


        Map<String,Account> accountMap = new HashMap<>();
        for(int i = 0 ; i <accounts.size();i++){
            accountMap.put(EncryptionTool.encrypt(accounts.get(i).getId()+""), accounts.get(i));
        }
        model.addAttribute("pages",new int[pageOfAccounts.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("email",email);
        model.addAttribute("map",accountMap);
        return "accounts";
    }

    @GetMapping(path = "/accountDetail")
    public String getOneAccount(String id,Model model){
        System.out.println(id);
        String ids =EncryptionTool.decrypt(id);
        Long a = Long.valueOf(ids);
        Account account = accountService.getOneAccount(a);
        model.addAttribute("account",account);
        model.addAttribute("encryptedID",EncryptionTool.encrypt(account.getId()+""));
        return "account";
    }

    @GetMapping(path = "/deleteAccount")
    public String deleteAccount(Long id){
        accountService.deleteAccount(id);
        return "redirect:/accounts";
    }

    @GetMapping(path = "/withdrawForm")
    public String withdrawMoney(String id,Model model){
        Long ID = Long.valueOf(EncryptionTool.decrypt(id));
        Account account=accountService.getOneAccount(ID);
        model.addAttribute("account",account);
        return "withdrawform";
    }

    @PostMapping(path = "/withdraw")
    public String withdraw(Model model, @ModelAttribute("withdrawingModel")WithdrawingModel withdrawingModel,
                           BindingResult bindingResult,
                           @ModelAttribute("id") Long id,
                           @ModelAttribute("error") String error,
                           RedirectAttributes redirectAttributes){

        String err = "";

        redirectAttributes.addFlashAttribute("id",id);
        if(bindingResult.hasErrors()){
            return "redirect:/withdrawForm?id="+EncryptionTool.encrypt(withdrawingModel.getId()+"");
        }
        Account account = accountService.getOneAccount(withdrawingModel.getId());
        double amount = withdrawingModel.getAmount();
        if(amount<=0){
            err="Invalide Amount";
            redirectAttributes.addFlashAttribute("error",err);
            return "redirect:/withdrawForm?id="+EncryptionTool.encrypt(withdrawingModel.getId()+"");
        }else if(amount < 100){
            err="You can not withdraw less than 100";
            redirectAttributes.addFlashAttribute("error",err);
            return "redirect:/withdrawForm?id="+EncryptionTool.encrypt(withdrawingModel.getId()+"");
        }else if(amount> account.getSold()){
            err="Your balance is less than this amount";
            redirectAttributes.addFlashAttribute("error",err);
            return "redirect:/withdrawForm?id="+EncryptionTool.encrypt(withdrawingModel.getId()+"");
        }

        withdrawingService.withdrawOperation(withdrawingModel.getId(),withdrawingModel.getAmount());

        return "redirect:/accounts";
    }

    @GetMapping(path = "/depositForm")
    public String depositMoney(String id,Model model){
        Long ID = Long.valueOf(EncryptionTool.decrypt(id));
        Account account=accountService.getOneAccount(ID);
        model.addAttribute("account",account);
        return "depositform";
    }

    @PostMapping(path = "/deposit")
    public String deposit(Model model, @ModelAttribute("depositModel") DepositingModel depositingModel,
                           BindingResult bindingResult,
                           @ModelAttribute("id") Long id,
                           @ModelAttribute("error") String error,
                           RedirectAttributes redirectAttributes){

        String err = "";

        redirectAttributes.addFlashAttribute("id",id);
        if(bindingResult.hasErrors()){
            return "redirect:/depositForm?id="+EncryptionTool.encrypt(depositingModel.getId()+"");
        }
        Account account = accountService.getOneAccount(depositingModel.getId());
        double amount = depositingModel.getAmount();
        if(amount<=0){
            err="Invalide Amount";
            redirectAttributes.addFlashAttribute("error",err);
            return "redirect:/depositForm?id="+EncryptionTool.encrypt(depositingModel.getId()+"");
        }else if(amount < 100){
            err="You can not deposit less than 100";
            redirectAttributes.addFlashAttribute("error",err);
            return "redirect:/depositForm?id="+EncryptionTool.encrypt(depositingModel.getId()+"");
        }else if(amount> 50000){
            err="Your can not deposit greater than 50000, please visit any agency to handle your request";
            redirectAttributes.addFlashAttribute("error",err);
            return "redirect:/depositForm?id="+EncryptionTool.encrypt(depositingModel.getId()+"");
        }

        depositingService.depositOperation(account.getId(),depositingModel.getAmount());

        return "redirect:/accounts";
    }


}
