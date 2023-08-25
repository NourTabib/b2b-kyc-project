package com.hydatis.KycmicroserviceCQRS.query.document;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Activite {
    private Long id;
    private LocalDateTime dateEntree;
    private String formeJuridique;
    private List<Produit> produits;
}
