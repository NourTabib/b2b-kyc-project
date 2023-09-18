package com.hydatis.KycmicroserviceCQRS.command.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.hydatis.KycmicroserviceCQRS.command.model.enums.TypeDocument;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long idDoc;
    private LocalDateTime dateEmission;
    private LocalDateTime dateExpiration;
    private String lieuEmission;
    @Enumerated(EnumType.STRING)
    private TypeDocument typeDocument;
    @OneToOne(mappedBy = "document", cascade = CascadeType.ALL)
    @JsonBackReference
    private AgentPersonnePhysique titulaireDuDocument;
}