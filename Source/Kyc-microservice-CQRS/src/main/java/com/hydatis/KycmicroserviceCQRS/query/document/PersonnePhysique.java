package com.hydatis.KycmicroserviceCQRS.query.document;


import lombok.*;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PersonnePhysique {
    private Integer id;
    private String nom;
    private String prenom;
    private LocalDateTime dateDeNaissance;
    private String nationalite;
    private String payResidence;
    private String adressePerso;
    private Long numCIN;
    private LocalDateTime dateEmissionCIN;
    private String  lieuEmissionCIN;
    private Double pourcentageCapitalDetenu;
}
