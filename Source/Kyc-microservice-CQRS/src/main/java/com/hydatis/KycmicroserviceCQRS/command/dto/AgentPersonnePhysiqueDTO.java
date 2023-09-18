package com.hydatis.KycmicroserviceCQRS.command.dto;

import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class AgentPersonnePhysiqueDTO {

    @NotNull
    private Step1 step1;

    @Valid
    private Step2 step2;

    @Valid // This annotation is used to validate nested objects
    private Step3 step3;

    @NotNull
    private Step4 step4;


}
