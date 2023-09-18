package com.hydatis.KycmicroserviceCQRS.command.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.hydatis.KycmicroserviceCQRS.command.model.enums.EtatDeCompte;
import com.hydatis.KycmicroserviceCQRS.command.model.enums.SourceAlimentation;
import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "comptes")
public class Compte {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idCompte;
    private String raison;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private SourceAlimentation sourceAlimentation;
    private String autreSourceAlimentationValue;
    @Enumerated(EnumType.STRING)
    private EtatDeCompte typeDeCompte;
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JsonManagedReference
    private Operation operationDebit;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JsonManagedReference
    private Operation operationCredit;
    @OneToOne(mappedBy = "compte")
    @JsonBackReference
    private AgentPersonnePhysique titulaire;

}
