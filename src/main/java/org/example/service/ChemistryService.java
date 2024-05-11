package org.example.service;

import org.example.dto.ChemistryDto;
import org.example.entity.Chemistry;
import org.example.repository.ChemistryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChemistryService {
    private final ChemistryRepository repository;

    public ChemistryService(ChemistryRepository repository) {
        this.repository = repository;
    }

    public String addChemistry(ChemistryDto dto){
        try {
            Chemistry chemistry = new Chemistry();
            chemistry.setName(dto.getName());
            repository.save(chemistry);
        } catch (Exception e) {
            return "NOT ADDED" + e.getMessage();
        }
        return "ADDED";
    }

    public List<Chemistry> getAllChemistry(){
        return repository.findAll();
    }

    public Chemistry getChemistryById(Long id) {
        return repository.getById(id);
    }
}
