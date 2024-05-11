package org.example.repository;

import org.example.entity.Pharmacist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PharmacistRepository extends JpaRepository<Pharmacist, Long> {

//    public List<Pharmacist> findPharmacistByNameContainsIgnoreCase(String name);


}
