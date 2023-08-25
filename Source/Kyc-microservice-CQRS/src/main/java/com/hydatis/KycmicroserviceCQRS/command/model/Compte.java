package com.hydatis.KycmicroserviceCQRS.command.model;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCompte;
    private String raison;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private SourceAlimentation sourceAlimentation;
    private String autreSourceAlimentationValue;
    @Enumerated(EnumType.STRING)
    private EtatDeCompte typeDeCompte;
    @OneToOne
    private Operation operationDebit;

    @OneToOne
    private Operation operationCredit;
    @OneToOne(mappedBy = "compte")
    private AgentPersonnePhysique titulaire;

}
