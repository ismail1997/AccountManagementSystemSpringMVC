package com.ismail.accountsystemspringmvc.services;

import com.ismail.accountsystemspringmvc.entities.Operation;
import com.ismail.accountsystemspringmvc.entities.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IOperationService {
    public List<Operation> getOperations();
    public Operation getOneOperation(Long id);
    public boolean addOperation(Operation operation);
    public boolean deleteOperation(Long id);
    public boolean updateOperation(Operation operation, Long id);
    public Page<Operation> getOperationsByAccountCode(String accountCode, int page, int size);
}
