package com.hydatis.KycmicroserviceCQRS.command.model;
import lombok.*;

import javax.persistence.*;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table
public class SocietesAffilees {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String SecteurActivite;
    private Double pourcentageCapitalDetenu;


    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "societe_id")
    private AgentPersonneMorale societe;


}
