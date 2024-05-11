package org.example.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PharmacistDto {
    private String name;
    private Long chemistry;
    private Double price;
    private String image;
}
