package com.hydatis.KycmicroserviceCQRS.query.document;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Produit {
    private Integer id;
    private String libelle;
    private Activite activity;
}
