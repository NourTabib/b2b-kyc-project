package com.hydatis.KycmicroserviceCQRS.query.document;


import com.hydatis.KycmicroserviceCQRS.command.model.enums.TypeAgent;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CategorieSocioProfesionnelle {
    private Long id;
    private TypeAgent typeAgent;
    private String raisonSociale;
    private String adresseProfessionelle;
    private String telephone;
    private String email;
    private String typeActivite;
    private String zoneGeo;
}
