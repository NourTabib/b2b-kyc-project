package com.hydatis.KycmicroserviceCQRS.query.document;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class demandeEngagement {
    private Long id;

    private LocalDateTime dateDemande;
    private Integer frausScore;
    private Decision decision;
}
