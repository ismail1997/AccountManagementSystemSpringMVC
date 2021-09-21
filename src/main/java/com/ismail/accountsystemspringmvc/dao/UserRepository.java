package com.ismail.accountsystemspringmvc.dao;

import com.ismail.accountsystemspringmvc.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    public Page<User> findByEmailContains(String keyword, Pageable pageable);
    public Page<User> findByFirstNameContains(String keyword , Pageable pageable);
}
