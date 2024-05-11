package org.example.repository;

import org.example.entity.Chemistry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChemistryRepository extends JpaRepository<Chemistry, Long> {
}
