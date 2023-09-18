package com.hydatis.KycmicroserviceCQRS.command.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.hydatis.KycmicroserviceCQRS.command.model.enums.TypeOperation;
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
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Double montantMoyenHebdo;
    private Double montantMoyenMensuels;
    private Double montantMoyenJournaliers;
    private Boolean virementInterne;
    private Boolean virementBancairePostaux;
    private Boolean cash;
    private Boolean virement;


    @Column(nullable = true)
    private String autreMoyen;
    @Enumerated(EnumType.STRING)
    private TypeOperation typeOperation;
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER
    )
    @JsonBackReference
    private Compte compte;
}