package com.ismail.accountsystemspringmvc.controllers;


import com.ismail.accountsystemspringmvc.entities.Account;
import com.ismail.accountsystemspringmvc.entities.User;
import com.ismail.accountsystemspringmvc.services.IAccountService;
import com.ismail.accountsystemspringmvc.services.IUserService;
import com.ismail.accountsystemspringmvc.utils.HashPassword;
import com.ismail.accountsystemspringmvc.utils.Parameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private IUserService userService;
    @Autowired
    private IAccountService accountService;

    @GetMapping(path = "/users")
    public String getUsers(Model model){
        List<User> users = userService.getUsers();
        model.addAttribute("users",users);
        return "users";
    }
    @GetMapping(path = "/newUser")
    public String newUserForm(Model model){
        model.addAttribute("user",new User());
        return "newuserform";
    }
    @PostMapping("/save")
    public String save(Model model, @Valid User user, BindingResult bindingResult){
        System.out.println(bindingResult);
        if(bindingResult.hasErrors()){
            return "newuserform";
        }
        user.setUserCode(Parameters.generateUserCode(user.getFirstName()));
        user.setActive(true);
        user.setUserType("USER");
        userService.addUser(user);
        Account account = new Account();
        account.setSold(Parameters.generateRandomInteger(0,200));
        account.setAccountType("Single Account");
        account.setUser(user);
        account.setActive(false);
        account.setCreationDate(LocalDate.now());
        account.setAccountCode(Parameters.generateAccountCode());
        accountService.addAccount(account);
        return "redirect:/users";
    }

    @GetMapping(path = "/")
    public String defaultPage(){
        return "redirect:/users";
    }


    @GetMapping(path = "/profile")
    public String userDetails(Model model,Long id){
        if(id==null) return "redirect:/";
        User user =userService.getOneUser(id);
        model.addAttribute("user",user);
        return "profile";
    }

    @GetMapping(path = "/edit")
    public String edit(Model model,Long id ){
        if(id==null) return "/users";
        User user = userService.getOneUser(id);
        model.addAttribute("user",user);
        return "edituserform";
    }

    @PostMapping("/update")
    public String update(Model model, @Valid User user, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "edituserform";
        }

        userService.addUser(user);

        return "redirect:/users";
    }

    @GetMapping(path = "/delete")
    public String deleteUser(Long id){
        userService.deleteUser(id);
        return "redirect:/users";
    }
}
