package com.ismail.accountsystemspringmvc.services;

import com.ismail.accountsystemspringmvc.entities.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IUserService {
    public List<User> getUsers();
    public User getOneUser(Long id);
    public boolean addUser(User user);
    public boolean deleteUser(Long id);
    public boolean updateUser(User user, Long id);
    public Page<User> getUsersWhereEmailContains(String email,int page,int size);

}
