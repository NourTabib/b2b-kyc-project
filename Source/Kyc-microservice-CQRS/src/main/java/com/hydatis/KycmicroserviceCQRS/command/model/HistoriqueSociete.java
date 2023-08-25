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
@Entity(name = "historique_societe")
@Table(name = "historique_societes")
public class HistoriqueSociete {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime creationDate;
    @Enumerated(EnumType.STRING)
    private TypeObtention typeObtention;
    private String questionChangementDeStructure;
    private String questionSecteurActivite;
    private String questionIncidents;
    private String questionDifficulteSurLeMarcher;
    public enum TypeObtention {
        CREATION,ACQUISITION,FUSION
    }
}
