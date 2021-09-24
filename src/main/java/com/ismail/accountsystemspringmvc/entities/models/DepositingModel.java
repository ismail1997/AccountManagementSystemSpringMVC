package com.ismail.accountsystemspringmvc.entities.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data @ToString @AllArgsConstructor @NoArgsConstructor
public class DepositingModel {
    private Long id;
    private double amount;
}
