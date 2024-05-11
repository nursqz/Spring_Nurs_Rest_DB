package org.example.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Table(name = "snr_pharmacist")
@Entity
public class Pharmacist {

    @Getter
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;


    @Getter
    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private double price;
    @Column(name = "image")
    private String image;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "chemistry_id")
    private Chemistry chemistry;

//    public Chemistry getChemistry() {
//        return chemistry;
//    }
//
//    public void setChemistry(Chemistry chemistry) {
//        this.chemistry = chemistry;
//    }

}
