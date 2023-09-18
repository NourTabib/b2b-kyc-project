package com.hydatis.KycmicroserviceCQRS.command.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "agentPersonnePhysique")
public class AgentPersonnePhysique extends Personne{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private String nom;
    private String prenom;
    private LocalDateTime dateDeNaissance;
    private String adressePerso;
    private String addresseCourier;
    private Boolean estPPE;
    @Column(nullable = true)
    private String fonctionPpe;
    private Boolean estPPI;
    @Column(nullable = true)
    private String pays;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "compte_id")
    @JsonManagedReference
    private Compte compte;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "document_id")
    @JsonManagedReference
    private Document document;
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "categorie_socio_pro_id")
    @JsonManagedReference
    private CategorieSocioProfesionnelle categorieSocioProfesionnelle;
    private boolean estBeneficiareEffectifs;

    @OneToOne(cascade = CascadeType.ALL, optional = true,fetch = FetchType.EAGER)
    @JsonManagedReference
    private AgentPersonnePhysique beneficiaireEffectif;


    @OneToOne(cascade = CascadeType.ALL, mappedBy ="beneficiaireEffectif",optional = true,fetch = FetchType.EAGER)
    @JsonBackReference
    private AgentPersonnePhysique titulaireDuCompte;

    @ManyToMany(fetch = FetchType.EAGER)
    @JsonManagedReference
    @JoinTable(
            name = "PP_BANQUE_EN_RELATION",
            joinColumns = @JoinColumn(name = "personne_physique_id"),
            inverseJoinColumns = @JoinColumn(name = "banque_id")
    )
    private List<Banque> banqueEnRelation;

    @OneToOne(mappedBy = "agentPersonnePhysique", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JsonBackReference
    private DemandeEngagement demandeEngagement;





}
