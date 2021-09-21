package com.ismail.accountsystemspringmvc.services;

import com.ismail.accountsystemspringmvc.dao.UserRepository;
import com.ismail.accountsystemspringmvc.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class UserServiceImpl implements IUserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getOneUser(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public boolean addUser(User user) {
        userRepository.save(user);
        return true;
    }

    @Override
    public boolean deleteUser(Long id) {
        userRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean updateUser(User user, Long id) {
        return false;
    }

    @Override
    public Page<User> getUsersWhereEmailContains(String email,int page,int size) {
        return userRepository.findByEmailContains(email, PageRequest.of(page, size));
    }
}
