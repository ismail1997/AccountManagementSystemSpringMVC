package com.ismail.accountsystemspringmvc.dao;

import com.ismail.accountsystemspringmvc.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
