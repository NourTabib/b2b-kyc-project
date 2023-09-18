package com.hydatis.KycmicroserviceCQRS.command.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Step1{
    private String firstName;
    private String lastName;
    private LocalDateTime ddn;
    private String nationalite;
    private String adressePersonnelle;
    private String adresseCourrier;
    private boolean resident;
    private String paysResidence;
    private Long phone;
    private Long docid;
    private String documentType;
    private LocalDateTime dateEmission;
    private String lieuEmission;
    private String adresseEmail;
    private boolean PPE;
    private String fonction;
    private boolean PPI;
    private String pays;
    private String raison;
    private String agentType;
    private String employeur;
    private String adressePro;
    private String telFaxPro;
    private String emailPro;
    private String typeActivite;
    private String zoneActivite;

}
