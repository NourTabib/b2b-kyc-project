package com.hydatis.KycmicroserviceCQRS.query.document;

import lombok.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Document
@ToString
public class HistoriqueSociete {
    @Indexed
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
