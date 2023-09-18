package com.hydatis.KycmicroserviceCQRS.command.dto;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter

public class Step3 {
    private List<Bank> banks;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Getter
    @Setter
    public static class Bank {
        private String banque;
        private String adressebanque;
        private String code;
        private String numCompte;
    }
}
