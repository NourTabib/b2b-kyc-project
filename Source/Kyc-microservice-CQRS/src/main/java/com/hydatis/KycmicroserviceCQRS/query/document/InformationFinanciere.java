package com.hydatis.KycmicroserviceCQRS.query.document;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class InformationFinanciere {
    private Integer id;

    private Double capitalSociete;
    private Double chiffreAffaireTotal;
    private Double TotalPassif;
    private Double resultatNet;

}
