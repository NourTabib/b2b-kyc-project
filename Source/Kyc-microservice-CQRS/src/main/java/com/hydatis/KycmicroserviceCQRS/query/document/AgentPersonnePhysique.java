package com.hydatis.KycmicroserviceCQRS.query.document;

import lombok.*;
import org.springframework.data.mongodb.core.index.Indexed;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@org.springframework.data.mongodb.core.mapping.Document
public class AgentPersonnePhysique {
    @Indexed(unique = true)
    private Long id;
    private String nationalite;
    private String residence;
    private Boolean estResident;
    private String telephone;
    private String email;
    private String nom;
    private String prenom;
    private LocalDateTime dateDeNaissance;
    private String adressePerso;
    private String addresseCourier;
    private Boolean estPPE;
    private String fonctionPpe;
    private Compte compte;
    private Document document;
    private CategorieSocioProfesionnelle categorieSocioProfesionnelle;
    private Boolean estBeneficiareEffectifs;
    private AgentPersonnePhysique beneficiaireEffectifs;
    private List<Banque> banqueEnRelation;
}
