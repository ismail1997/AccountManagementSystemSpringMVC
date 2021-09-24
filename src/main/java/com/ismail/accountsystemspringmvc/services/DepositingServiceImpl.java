package com.ismail.accountsystemspringmvc.services;

import com.ismail.accountsystemspringmvc.dao.OperationRepository;
import com.ismail.accountsystemspringmvc.entities.Account;
import com.ismail.accountsystemspringmvc.entities.Operation;
import com.ismail.accountsystemspringmvc.entities.OperationType;
import com.ismail.accountsystemspringmvc.utils.Parameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@Transactional
public class DepositingServiceImpl implements IDepositingService {
    @Autowired
    private OperationRepository operationRepository;
    @Autowired
    private IAccountService accountService;
    @Override
    public boolean depositOperation(Long id, double amount) {
        //get the account by id
        Account account=accountService.getOneAccount(id);
        //set the new amount of account
        double newAmount = account.getSold()+amount;
        account.setSold(newAmount);
        //save the new Account
        accountService.addAccount(account);
        //create the operation
        Operation operation = new Operation();
        operation.setOperationType(OperationType.DEPOSITING_OPERATION);
        operation.setOperationCode(Parameters.generateOperationCode());
        operation.setOperationMessage("Depositing the amount of "+amount+" to account with code ="+account.getAccountCode());
        operation.setOperationDate(LocalDate.now());
        operation.setAccount(account);
        operation.setOperationAmount(amount);
        operationRepository.save(operation);
        return true;
    }
}
