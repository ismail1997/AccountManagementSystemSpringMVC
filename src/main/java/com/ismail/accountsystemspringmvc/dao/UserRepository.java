package com.ismail.accountsystemspringmvc.dao;

import com.ismail.accountsystemspringmvc.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User,Long> {
    public Page<User> findByEmailContains(String keyword, Pageable pageable);
    @Query("select u from User u where u.email =?1")
    public User findUserByEmailEquals(String email);

}
