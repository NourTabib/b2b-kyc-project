package com.hydatis.KycmicroserviceCQRS.query.document;

import lombok.*;
import com.hydatis.KycmicroserviceCQRS.query.document.Utilisateur;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Client extends Utilisateur{
    private Long id ;
    private List <demandeEngagement> demandeEngagements;
}
