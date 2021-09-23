package com.ismail.accountsystemspringmvc.services;

import com.ismail.accountsystemspringmvc.dao.AccountRepository;
import com.ismail.accountsystemspringmvc.dao.UserRepository;
import com.ismail.accountsystemspringmvc.entities.Account;
import com.ismail.accountsystemspringmvc.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class AccountServiceImpl implements IAccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public Account getOneAccount(Long id) {
        return accountRepository.findById(id).get();
    }

    @Override
    public boolean addAccount(Account account) {
        accountRepository.save(account);
        return true;
    }

    @Override
    public boolean deleteAccount(Long id) {
        accountRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean updateAccount(Account account, Long id) {

        return false;
    }

    @Override
    public Page<Account> getAccountsByUserID(Long id, int page, int size) {
        if(id==null) return accountRepository.findAll(PageRequest.of(page,size));
        User user = userRepository.findById(id).get();
        if(user==null) return null;
        return accountRepository.findByUser(user, PageRequest.of(page,size));
    }

    @Override
    public Page<Account> getAccountsByUserEmail(String email, int page, int size) {
        if(email==null || email.equals("") || email.isEmpty()) return accountRepository.findAll(PageRequest.of(page,size));
        User user = userRepository.findUserByEmailEquals(email);
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+user);
        if(user==null) return accountRepository.findAll(PageRequest.of(page,size));

        return accountRepository.findByUser(user,PageRequest.of(page,size));
    }
}
