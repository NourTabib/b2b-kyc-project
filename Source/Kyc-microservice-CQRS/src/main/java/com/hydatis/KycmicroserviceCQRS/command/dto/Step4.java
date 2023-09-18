package com.hydatis.KycmicroserviceCQRS.command.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter

public class Step4 {
    private boolean certification;
    private String nomPrenomAgent;



}
