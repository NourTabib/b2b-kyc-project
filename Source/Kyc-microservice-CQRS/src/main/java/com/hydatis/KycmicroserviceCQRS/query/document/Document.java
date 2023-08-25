package com.hydatis.KycmicroserviceCQRS.query.document;

import lombok.*;

import java.time.LocalDateTime;


@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Document {
    private Long idDoc;
    private LocalDateTime dateEmission;
    private LocalDateTime dateExpiration;
    private String lieuEmission;
    private TypeDocument typeDocument;
    public enum TypeDocument {
        PASSPORT,CIN
    }
}
