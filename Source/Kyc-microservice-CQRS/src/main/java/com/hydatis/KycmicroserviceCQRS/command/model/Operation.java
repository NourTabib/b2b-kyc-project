package com.hydatis.KycmicroserviceCQRS.command.model;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double montantMoyenHebdo;
    private Double montantMoyenMensuels;
    private Double montantMoyenJournaliers;
    private Boolean virementBancairePostaux;
    private Boolean virementInterne;
    private Boolean cash;
    private Boolean virement;

    @Column(nullable = true)
    private String autreMoyen;
    @Enumerated(EnumType.STRING)
    private TypeOperation typeOperation;
    @ManyToOne
    private Compte compte;
}