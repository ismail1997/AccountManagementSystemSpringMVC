package com.ismail.accountsystemspringmvc.services;

public interface IDepositingService {
    public default boolean depositOperation(Long id, double amount) {
        return false;
    }
}
