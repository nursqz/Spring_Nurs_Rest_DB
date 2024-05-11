package org.example.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "snr_chemistry")
public class Chemistry {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "chemistry")
    private List<Pharmacist> pharmacistList;

    public Chemistry() {
        this.id = id;
    }

    public Chemistry(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Pharmacist> getPharmacistList() {
        return pharmacistList;
    }

    public void setPharmacistList(List<Pharmacist> pharmacistList) {
        this.pharmacistList = pharmacistList;
    }
}
