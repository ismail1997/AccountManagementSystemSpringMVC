package com.ismail.accountsystemspringmvc.dao;

import com.ismail.accountsystemspringmvc.entities.Account;
import com.ismail.accountsystemspringmvc.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

public interface AccountRepository extends JpaRepository<Account,Long> {
    public Page<Account> findByUser(User user, Pageable pageable);
    public Account findAccountByAccountCode(String accountCode);
}
