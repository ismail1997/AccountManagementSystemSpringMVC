package com.ismail.accountsystemspringmvc.entities;

import com.ismail.accountsystemspringmvc.utils.Parameters;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;

@Data @Entity @NoArgsConstructor
@AllArgsConstructor @ToString
public class Account implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String accountCode;
    private LocalDate creationDate;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    private double sold;
    private String accountType;
    private boolean active;
    @OneToMany(mappedBy = "account")
    private Collection<Operation> operations;


}
