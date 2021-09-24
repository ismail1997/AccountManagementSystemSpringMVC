package com.ismail.accountsystemspringmvc.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import java.time.LocalDate;
import java.util.Date;

@Entity @Data @NoArgsConstructor @AllArgsConstructor
public class Operation {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String operationCode;
    @Temporal(TemporalType.TIMESTAMP)
    private Date operationDate;
    private String operationMessage;
    @Enumerated(EnumType.ORDINAL)
    private OperationType operationType;
    private double operationAmount;
    @ManyToOne
    private Account account;
}
