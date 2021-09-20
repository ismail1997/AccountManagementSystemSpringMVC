package com.ismail.accountsystemspringmvc.services;

import com.ismail.accountsystemspringmvc.dao.AccountRepository;
import com.ismail.accountsystemspringmvc.dao.ReclamationRepository;
import com.ismail.accountsystemspringmvc.dao.UserRepository;
import com.ismail.accountsystemspringmvc.entities.Account;
import com.ismail.accountsystemspringmvc.entities.Reclamation;
import com.ismail.accountsystemspringmvc.entities.User;
import com.ismail.accountsystemspringmvc.utils.HashPassword;
import com.ismail.accountsystemspringmvc.utils.Parameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.stream.Stream;

@Transactional
@Service
public class AccountSystemInitService implements IAccountSystemInitService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ReclamationRepository reclamationRepository;

    @Override
    public void initUsers() {
        Stream.of("Bouaddi,Ismail,bouaddi1997@gmail.com,+212611298559,Ait Oumanar El maader Tiznit Maroc,admin,1997@01@27",
                "Jochwa,Kimich,kimich@germany.com,+313452698574,Bayern Munich Germany,user,1990@02@13",
                "Jackson,Michael,kingOfPop@gmail.mj,+131345789620,San Fransisco USA,user,1958@12@10").forEach(name->{
            String firstName = "";
            String lastName ="";
            String email ="";
            String phoneNumber ="";
            String address ="";
            String userType="";
            String birthdays = "";
            String[] names = name.split(",");
            firstName=names[1]; lastName=names[0];email=names[2];phoneNumber=names[3];address=names[4];
            userType=names[5];birthdays=names[6];

            User user = new User();
            user.setUserCode(Parameters.generateUserCode(firstName));
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEmail(email);
            user.setAddress(address);
            user.setPhoneNumber(phoneNumber);
            user.setUserType(userType.toUpperCase());
            user.setPassword(HashPassword.cryptAndDecryptPassword(firstName.toLowerCase()));
            user.setActive(true);

            //birthday
            String bds [] = birthdays.split("@");
            user.setBirthDate(LocalDate.of(Integer.parseInt(bds[0]),Integer.parseInt(bds[1]),Integer.parseInt(bds[2])));

            userRepository.save(user);
        });

    }

    @Override
    public void initAccounts() {
        userRepository.findAll().forEach(user->{
            Account account = new Account();
            account.setUser(user);
            account.setAccountCode(Parameters.generateAccountCode());
            account.setActive(true);
            account.setAccountType("Single Account");
            account.setCreationDate(LocalDate.now());
            account.setSold(Parameters.generateRandomInteger(2500,15000));
            accountRepository.save(account);
        });
    }

    @Override
    public void initReclamations() {
        userRepository.findAll().forEach(user->{
            if(!user.getUserType().equals("ADMIN")){
                Reclamation reclamation = new Reclamation();
                reclamation.setReclamationDate(LocalDate.now());
                reclamation.setUser(user);
                reclamation.setReclamationMessage("Find an error in my account");
                reclamation.setAnswered(false);
                reclamationRepository.save(reclamation);
            }
        });
    }
}
