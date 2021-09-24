package com.ismail.accountsystemspringmvc.services;

import com.ismail.accountsystemspringmvc.entities.Operation;

public interface IWithdrawingService {
    public boolean withdrawOperation(Long id,double amount);
}
