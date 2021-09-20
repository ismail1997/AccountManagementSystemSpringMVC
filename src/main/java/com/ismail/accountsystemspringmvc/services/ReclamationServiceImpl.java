package com.ismail.accountsystemspringmvc.services;

import com.ismail.accountsystemspringmvc.dao.ReclamationRepository;
import com.ismail.accountsystemspringmvc.entities.Reclamation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class ReclamationServiceImpl implements IReclamationService {
    @Autowired
    private ReclamationRepository reclamationRepository;
    @Override
    public List<Reclamation> getReclamations() {
        return reclamationRepository.findAll();
    }

    @Override
    public Reclamation getOneReclamation(Long id)
    {
        return reclamationRepository.findById(id).get();
    }

    @Override
    public boolean addReclamation(Reclamation reclamation) {
        reclamationRepository.save(reclamation);
        return true;
    }

    @Override
    public boolean deleteReclamation(Long id) {
        reclamationRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean updateReclamation(Reclamation reclamation, Long id) {
        return false;
    }
}
