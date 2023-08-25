package com.hydatis.KycmicroserviceCQRS.command.model;

import lombok.*;

import javax.persistence.*;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "AgentPersonneMorales")
public class AgentPersonneMorale extends Personne{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numIdUnique;
    @Column(nullable = false)
    private String raisonSociale;
    @Column(nullable = false)
    private LocalDateTime dateExtraitRNE;
    @Column(nullable = false)
    private String matriculeFiscale;
    @Column(nullable = false, unique = true )
    private Long codeDouane;
    @Column(nullable = false)
    private LocalDateTime dateCreation;
    @Column(nullable = false)
    private  String adresseSiégeSociale;
    @Column(nullable = false, unique = true )
    private URL siteWeb;
    @Column(nullable = false, unique = true )
    private String fax;
    @Column(nullable = false)
    private String groupe;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "compte_id")
    private Compte compte;



    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "activite_id")
    private Activite activite;

    @ManyToMany
    @JoinTable(
            name = "PM_BANQUE_EN_RELATION",
            joinColumns = @JoinColumn(name = "personne_Morale_id"),
            inverseJoinColumns = @JoinColumn(name = "banque_id")
    )
    private List<Banque> banques;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy = "societe")
    List<SocietesAffilees> societesAffilees;

    @OneToOne
    @JoinColumn(name = "informations_id")
    private InformationsFinanciere informationsFinanciere;

    @OneToMany(mappedBy = "agentPersonneMorale", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PersonnePhysique> personnePhysiqueBeneficaire;

    @OneToMany(mappedBy = "societe", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<PersonnePhysique> personnePhysiqueActionneur;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "historique_id")
    private HistoriqueSociete historiqueSociete;

}


