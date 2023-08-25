package com.hydatis.KycmicroserviceCQRS.query.document;

import com.hydatis.KycmicroserviceCQRS.command.model.enums.TypeOperation;
import com.hydatis.KycmicroserviceCQRS.command.model.enums.TypeOperation;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Operation {
    private Long id;
    private Double montantMoyenHebdo;
    private Double montantMoyenMensuels;
    private Double montantMoyenJournaliers;
    private Boolean virementBancairePostaux;
    private Boolean virementInterne;
    private Boolean cash;
    private Boolean virement;
    private String autreMoyen;
    private TypeOperation typeOperation;
}
