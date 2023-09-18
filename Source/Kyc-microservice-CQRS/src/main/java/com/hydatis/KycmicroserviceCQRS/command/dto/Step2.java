package com.hydatis.KycmicroserviceCQRS.command.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter

public class Step2{
    private String comptePaiement;
    private boolean virementInterne;
    private boolean virementBancaire;
    private boolean autreMoyenPaiement;
    private String autreMoyenPaiementText;
    private double montantsJournaliers;
    private double montantsHebdomadaires;
    private double montantsMensuels;
    private boolean cash;
    private boolean virementdebit;
    private boolean autreMoyendebit;
    private String autreMoyendebitText;
    private String montantsJournaliersdebit;
    private double montantsHebdomadairesdebit;
    private double montantsMensuelsdebit;
    private boolean virementespeces;
    private boolean revenusPro;
    private boolean produitInvestissement;
    private boolean autresource;
    private String autreSourceText;

    private boolean isBeneficaire;
    private String lastNameBeneficaire;
    private String firstNameBeneficaire;
    private String docIdBeneficaire;
    private LocalDateTime ddnBeneficaire;
    private String documentTypeBeneficaire;
    private LocalDateTime dateEmissionBeneficaire;
    private String lieuEmissionBeneficaire;
    private LocalDateTime dateExpirationBeneficaire;
    private String nationaliteBeneficaire;
    private boolean residentBeneficaire;
    private String paysResidenceBeneficaire;
    private boolean pPEBeneficaire;
    private String fonctionBeneficaire;



}
