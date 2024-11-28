package com.example.intranetecovis.services;

import com.example.intranetecovis.models.Nouvelle;
import com.example.intranetecovis.repositories.INouvelleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NouvelleService {
    private final INouvelleRepository nouvelleRepository;

    public NouvelleService(INouvelleRepository nouvelleRepository) {
        this.nouvelleRepository = nouvelleRepository;
    }

    public List<Nouvelle> getAll() {
        return nouvelleRepository.findAll();
    }

    public Nouvelle save(Nouvelle nouvelle) {
        return nouvelleRepository.save(nouvelle);
    }

    public Optional<Nouvelle> findById(int id) {
        return nouvelleRepository.findById(id);
    }

    public void deleteById(int id) {
        nouvelleRepository.deleteById(id);
    }
}
