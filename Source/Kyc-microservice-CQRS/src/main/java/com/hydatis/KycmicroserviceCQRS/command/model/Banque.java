package com.hydatis.KycmicroserviceCQRS.command.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "banques")
public class Banque {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nomBanque;
    private String adresse;
    private String swift;
    private String rib;
    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY,mappedBy = "banqueEnRelation")
    private List<AgentPersonnePhysique> clientPersonnePhysique;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY,mappedBy = "banques")
    private List<AgentPersonneMorale> personneMorales;
}