package com.ismail.accountsystemspringmvc.dao;

import com.ismail.accountsystemspringmvc.entities.Reclamation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReclamationRepository extends JpaRepository<Reclamation,Long> {
}
