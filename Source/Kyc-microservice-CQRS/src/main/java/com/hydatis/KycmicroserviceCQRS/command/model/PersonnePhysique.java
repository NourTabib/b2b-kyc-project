package com.hydatis.KycmicroserviceCQRS.command.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "PersonnePhysiques")
public class PersonnePhysique {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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



    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "actionneur_id")
    private AgentPersonneMorale societe;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "beneficaire_id")
    private AgentPersonneMorale agentPersonneMorale;



}
