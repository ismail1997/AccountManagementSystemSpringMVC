package com.ismail.accountsystemspringmvc.services;

import com.ismail.accountsystemspringmvc.entities.Reclamation;
import com.ismail.accountsystemspringmvc.entities.User;

import java.util.List;

public interface IReclamationService {
    public List<Reclamation> getReclamations();
    public Reclamation getOneReclamation(Long id);
    public boolean addReclamation(Reclamation reclamation);
    public boolean deleteReclamation(Long id);
    public boolean updateReclamation(Reclamation reclamation, Long id);
}
