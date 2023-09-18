package com.hydatis.KycmicroserviceCQRS.command.model;

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
@Table(name = "responsables")
public class Responsable extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true )
    private String signature;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<DemandeEngagement> demandeEngagements;
    @OneToMany(mappedBy = "prisePar")
    private List<Decision> decisions;
}

