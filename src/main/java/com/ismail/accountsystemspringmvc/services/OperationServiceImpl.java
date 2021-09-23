package com.ismail.accountsystemspringmvc.services;

import com.ismail.accountsystemspringmvc.dao.AccountRepository;
import com.ismail.accountsystemspringmvc.dao.OperationRepository;
import com.ismail.accountsystemspringmvc.dao.UserRepository;
import com.ismail.accountsystemspringmvc.entities.Account;
import com.ismail.accountsystemspringmvc.entities.Operation;
import com.ismail.accountsystemspringmvc.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OperationServiceImpl implements IOperationService {

    @Autowired
    private OperationRepository operationRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public List<Operation> getOperations() {
        return operationRepository.findAll();
    }

    @Override
    public Operation getOneOperation(Long id) {
        return operationRepository.getById(id);
    }

    @Override
    public boolean addOperation(Operation operation) {
        operationRepository.save(operation);
        return true;
    }

    @Override
    public boolean deleteOperation(Long id) {
        operationRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean updateOperation(Operation operation, Long id) {
        Operation operation1 = operationRepository.getById(id);
        if(operation1 ==null) return false;
        operationRepository.save(operation);
        return true;
    }

    @Override
    public Page<Operation> getOperationsByAccountCode(String accountCode, int page, int size) {
        Account account = accountRepository.findAccountByAccountCode(accountCode);
        if(account==null) return operationRepository.findAll(PageRequest.of(page,size));
        return operationRepository.findOperationByAccount(account,PageRequest.of(page,size));
    }
}
