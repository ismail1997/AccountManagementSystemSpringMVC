package com.ismail.accountsystemspringmvc;

import com.ismail.accountsystemspringmvc.dao.AccountRepository;
import com.ismail.accountsystemspringmvc.dao.ReclamationRepository;
import com.ismail.accountsystemspringmvc.dao.UserRepository;
import com.ismail.accountsystemspringmvc.entities.Account;
import com.ismail.accountsystemspringmvc.entities.Reclamation;
import com.ismail.accountsystemspringmvc.entities.User;
import com.ismail.accountsystemspringmvc.services.IAccountSystemInitService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;

@SpringBootApplication
public class AccountManagementSystemSpringMvcApplication implements CommandLineRunner {
//    @Autowired
//    IAccountSystemInitService accountSystemInitService;
    public static void main(String[] args) {
        SpringApplication.run(AccountManagementSystemSpringMvcApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        accountSystemInitService.initUsers();
//        accountSystemInitService.initAccounts();
//        accountSystemInitService.initReclamations();
    }
}
