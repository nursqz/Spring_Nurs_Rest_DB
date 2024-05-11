package org.example.dto;

public class ChemistryDto {
    private Long id;
    private String name;

    public ChemistryDto() {

    }

    public ChemistryDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
