package com.ismail.accountsystemspringmvc.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ismail.accountsystemspringmvc.utils.HashPassword;


import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;

@Entity
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

    public User() {

    }

    public User(Long id, String userCode, String firstName, String lastName, String phoneNumber, String email, String password, LocalDate birthDate, String address, String userType, boolean active, Collection<Account> accounts, Collection<Reclamation> reclamations) {
        this.id = id;
        this.userCode = userCode;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.birthDate = birthDate;
        this.address = address;
        this.userType = userType;
        this.active = active;
        this.accounts = accounts;
        this.reclamations = reclamations;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return HashPassword.cryptAndDecryptPassword(password);
    }

    public void setPassword(String password) {
        this.password = HashPassword.cryptAndDecryptPassword(password);
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Collection<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Collection<Account> accounts) {
        this.accounts = accounts;
    }

    public Collection<Reclamation> getReclamations() {
        return reclamations;
    }

    public void setReclamations(Collection<Reclamation> reclamations) {
        this.reclamations = reclamations;
    }
}
