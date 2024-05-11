package org.example.service;

import org.example.dto.PharmacistDto;
import org.example.entity.Chemistry;
import org.example.entity.Pharmacist;
import org.example.repository.PharmacistRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PharmacistService {

    private final PharmacistRepository repository;

    private final ChemistryService chemistryService;

    public PharmacistService(PharmacistRepository repository, ChemistryService chemistryService) {
        this.repository = repository;
        this.chemistryService = chemistryService;
    }

    public String addPharmacist(PharmacistDto dto) {
        if(!dto.getName().isBlank() && dto.getName().length()>2) {
            Pharmacist pharmacist = new Pharmacist();
            pharmacist.setName(dto.getName());

            Chemistry chemistry = chemistryService.getChemistryById(dto.getChemistry());
            pharmacist.setChemistry(chemistry);

            repository.save(pharmacist);
        }
        return "ADDED";
    }

    public List<Pharmacist> getAllPharmacist(){
        return repository.findAll();
    }
}
