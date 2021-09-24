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
import java.util.Date;

@Service
@Transactional
public class withdrawingServiceImpl implements IWithdrawingService {
    @Autowired
    private OperationRepository operationRepository;
    @Autowired
    private IAccountService accountService;
    @Override
    public boolean withdrawOperation(Long id,double amount) {
        //get the account by id
        Account account=accountService.getOneAccount(id);
        //set the new amount of account
        double newAmount = account.getSold()-amount;
        account.setSold(newAmount);
        //save the new Account
        accountService.addAccount(account);
        //create the operation
        Operation operation = new Operation();
        operation.setOperationType(OperationType.WITHDRAWING_OPERATION);
        operation.setOperationCode(Parameters.generateOperationCode());
        operation.setOperationMessage("Withdrawing the amount of "+amount+" DHs");
        operation.setOperationDate(new Date());
        operation.setAccount(account);
        operation.setOperationAmount(amount);
        operationRepository.save(operation);
        return true;
    }
}
