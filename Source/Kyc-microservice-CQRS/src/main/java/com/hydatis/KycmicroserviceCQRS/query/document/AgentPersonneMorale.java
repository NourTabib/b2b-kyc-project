package com.hydatis.KycmicroserviceCQRS.query.document;

import lombok.*;
import org.springframework.data.mongodb.core.index.Indexed;





import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@org.springframework.data.mongodb.core.mapping.Document
public class AgentPersonneMorale {
    private Long numIdUnique;
    private String nationalite;
    private String residence;
    private Boolean estResident;
    private String telephone;
    private String email;
    private String raisonSociale;
    private LocalDateTime dateExtraitRNE;
    private String matriculeFiscale;
    private Long codeDouane;
    private LocalDateTime dateCreation;
    private  String adresseSiégeSociale;
    private URL siteWeb;
    private String fax;
    private String groupe;
    private Compte compte;
    private List<Banque> banques;
    private Activite activite;
    private InformationFinanciere informationFinanciere;
    private SocietesAffilees societesAffilees;
    private List<PersonnePhysique> actionneurs;
    private List<PersonnePhysique> benificiaires;
    private HistoriqueSociete historiqueSociete;
}
