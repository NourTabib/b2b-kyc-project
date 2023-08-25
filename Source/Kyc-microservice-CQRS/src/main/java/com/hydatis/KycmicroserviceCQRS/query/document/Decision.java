package com.hydatis.KycmicroserviceCQRS.query.document;

import com.hydatis.KycmicroserviceCQRS.command.model.enums.TypeDecision;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Decision {
    private Long id;
    private LocalDateTime dateDecision;
    private TypeDecision decisionType;
}
