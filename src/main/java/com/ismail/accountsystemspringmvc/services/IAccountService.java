package com.ismail.accountsystemspringmvc.services;

import com.ismail.accountsystemspringmvc.entities.Account;
import com.ismail.accountsystemspringmvc.entities.User;

import java.util.List;

public interface IAccountService {
    public List<Account> getAccounts();
    public Account getOneAccount(Long id);
    public boolean addAccount(Account account);
    public boolean deleteAccount(Long id);
    public boolean updateAccount(Account account, Long id);
}
