package com.ismail.accountsystemspringmvc.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;

@Entity  @Data @AllArgsConstructor
@NoArgsConstructor @ToString
public class User implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userCode;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email ;
    private String password;
    private LocalDate birthDate;
    private String address;
    private String userType;
    private boolean active;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<Account> accounts;
    @OneToMany(mappedBy = "user" ,fetch = FetchType.LAZY
    ,cascade = CascadeType.ALL)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<Reclamation> reclamations;


}
