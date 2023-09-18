package com.hydatis.KycmicroserviceCQRS.command.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.hydatis.KycmicroserviceCQRS.command.model.enums.TypeAgent;
import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table
public class CategorieSocioProfesionnelle {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private TypeAgent typeAgent;
    private String raisonSociale;
    private String adresseProfessionelle;
    private String telephone;
    private String email;
    private String typeActivite;
    private String zoneGeo;
    @OneToOne(mappedBy = "categorieSocioProfesionnelle")
    @JsonBackReference
    private AgentPersonnePhysique agentPersonnePhysique;
}
