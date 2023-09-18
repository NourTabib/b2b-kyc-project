package com.hydatis.KycmicroserviceCQRS.command.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.hydatis.KycmicroserviceCQRS.command.model.enums.TypeDecision;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table
public class Decision {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer fraudScore;
    private LocalDateTime dateDecision;

    @OneToOne(fetch = FetchType.EAGER)
    @JsonManagedReference
    private DemandeEngagement demande;

    @Enumerated(EnumType.STRING)
    private TypeDecision decision;
    @ManyToOne( fetch = FetchType.EAGER)
    private Responsable prisePar;
}
