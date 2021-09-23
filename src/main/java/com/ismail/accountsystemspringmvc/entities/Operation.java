package com.ismail.accountsystemspringmvc.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity @Data @NoArgsConstructor @AllArgsConstructor
public class Operation {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String operationCode;
    private LocalDate operationDate;
    private String operationMessage;
    @ManyToOne
    private Account account;
}
