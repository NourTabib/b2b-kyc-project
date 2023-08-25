package com.hydatis.KycmicroserviceCQRS.query.document;


import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SocietesAffilees {
    private Integer id;

    private String SecteurActivite;
    private Double pourcentageCapitalDetenu;
}
