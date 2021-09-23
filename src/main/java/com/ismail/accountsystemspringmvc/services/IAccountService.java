package com.ismail.accountsystemspringmvc.services;

import com.ismail.accountsystemspringmvc.entities.Account;
import com.ismail.accountsystemspringmvc.entities.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IAccountService {
    public List<Account> getAccounts();
    public Account getOneAccount(Long id);
    public boolean addAccount(Account account);
    public boolean deleteAccount(Long id);
    public boolean updateAccount(Account account, Long id);
    public Page<Account> getAccountsByUserID(Long id,int page,int size);
    public Page<Account> getAccountsByUserEmail(String email,int page,int size);
}
