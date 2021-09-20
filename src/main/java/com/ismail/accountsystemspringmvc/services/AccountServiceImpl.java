package com.ismail.accountsystemspringmvc.services;

import com.ismail.accountsystemspringmvc.dao.AccountRepository;
import com.ismail.accountsystemspringmvc.entities.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class AccountServiceImpl implements IAccountService {
    @Autowired
    AccountRepository accountRepository;
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
}
